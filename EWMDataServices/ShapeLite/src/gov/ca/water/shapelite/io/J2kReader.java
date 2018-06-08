package gov.ca.water.shapelite.io;

import icc.ICCProfileException;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import colorspace.ColorSpace;
import colorspace.ColorSpaceException;
import gov.ca.water.shapelite.data.Band;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import jj2000.j2k.codestream.HeaderInfo;
import jj2000.j2k.codestream.reader.BitstreamReaderAgent;
import jj2000.j2k.codestream.reader.HeaderDecoder;
import jj2000.j2k.decoder.Decoder;
import jj2000.j2k.decoder.DecoderSpecs;
import jj2000.j2k.entropy.decoder.EntropyDecoder;
import jj2000.j2k.fileformat.reader.FileFormatReader;
import jj2000.j2k.image.BlkImgDataSrc;
import jj2000.j2k.image.DataBlkInt;
import jj2000.j2k.image.ImgDataConverter;
import jj2000.j2k.image.invcomptransf.InvCompTransf;
import jj2000.j2k.quantization.dequantizer.Dequantizer;
import jj2000.j2k.roi.ROIDeScaler;
import jj2000.j2k.util.ISRandomAccessIO;
import jj2000.j2k.util.ParameterList;
import jj2000.j2k.wavelet.synthesis.InverseWT;

public class J2kReader {

    private DataBlkInt db = new DataBlkInt();


    public J2kReader() {
    }

    /**
     * This expects the jp2 file to store three bands of color. If the image is
     * not a color image this will throw a J2kException.
     *
     * @param is
     * @return
     * @throws IOException
     * @throws J2kException
     */
    public Image decodeImage(InputStream is) throws IOException, J2kException {
        // write out the image
        BlkImgDataSrc decodedImage = decode(is);
        J2kWriter imwriter = new J2kWriter(decodedImage, 0, 1, 2);
        Image buf = imwriter.getImage();
        return buf;
    }

    /**
     * This is a more generic version that can handle any number of component
     * bands. This is more useful for DEM style elevation rasters and others.
     *
     * @param is
     * @return
     * @throws java.io.IOException
     * @throws gov.ca.water.shapelite.io.J2kException
     */
    public RasterDataset decodeRaster(InputStream is) throws IOException, J2kException {
        RasterDataset result = new RasterDataset();
        BlkImgDataSrc src = decode(is);

        for(int iBand = 0; iBand < src.getNumComps(); iBand++){
            int w = src.getCompImgWidth(iBand);
            int h = src.getCompImgHeight(iBand);
            Band resultBand = new Band(w, src.getImgHeight(), -99);
            int fb = src.getFixedPoint(iBand);
            int levShift = 1 << (src.getNomRangeBits(iBand)-1);
            int tOffx = src.getCompULX(iBand) -
            (int)Math.ceil(src.getImgULX()/(double)src.getCompSubsX(iBand));
            int tOffy = src.getCompULY(iBand) -
            (int)Math.ceil(src.getImgULY()/(double)src.getCompSubsY(iBand));

            // Initialize db
            for(int y = 0; y < h; y++){
                db.ulx = 0;
                db.uly = y;
                db.w = w;
                db.h = 1;

                do {
                    db = (DataBlkInt)src.getInternCompData(db,iBand);
                } while (db.progressive);

                double[][] data = resultBand.getData();
                for(int x = 0; x < w; x++){
                       data[y][x] = db.data[x];
                   }
            }
        }
        result.calculateMinMax();
        return result;
    }

    public BlkImgDataSrc decode(InputStream is) throws IOException, J2kException {
        ISRandomAccessIO in = new ISRandomAccessIO(is);
        ParameterList defpl = new ParameterList();
        String[][] param = Decoder.getAllParameters();

        for (int i = param.length - 1; i >= 0; i--) {
            if (param[i][3] != null) {
                defpl.put(param[i][0], param[i][3]);
            }
        }
        // Create parameter list using defaults
        ParameterList pl = new ParameterList(defpl);

		// **** File Format ****
        // If the codestream is wrapped in the jp2 fileformat, Read the
        // file format wrapper
        FileFormatReader ff = new FileFormatReader(in);
        ff.readFileFormat();
        if (ff.JP2FFUsed) {
            in.seek(ff.getFirstCodeStreamPos());
        }

        // **** header decoder ****
        HeaderInfo hi = new HeaderInfo();
        HeaderDecoder hd;
        DecoderSpecs decSpec;
        hd = new HeaderDecoder(in, pl, hi);
        decSpec = hd.getDecoderSpecs();
        // Get demixed bitdepths
        int nCompCod = hd.getNumComps();
        int[] depth = new int[nCompCod];
        for (int i = 0; i < nCompCod; i++) {
            depth[i] = hd.getOriginalBitDepth(i);
        }

        // **** Bit stream reader ****
        BitstreamReaderAgent breader = BitstreamReaderAgent.createInstance(in,
                hd, pl, decSpec, pl.getBooleanParameter("cdstr_info"), hi);

        // **** Entropy decoder ****
        EntropyDecoder entdec = hd.createEntropyDecoder(breader, pl);

        // **** ROI de-scaler ****
        ROIDeScaler roids = hd.createROIDeScaler(entdec, pl, decSpec);

        // **** Dequantizer ****
        Dequantizer deq = hd.createDequantizer(roids, depth, decSpec);

        // full page inverse wavelet transform
        InverseWT invWT = InverseWT.createInstance(deq, decSpec);
        int res = breader.getImgRes();
        invWT.setImgResLevel(res);

        // **** Data converter **** (after inverse transform module)
        ImgDataConverter converter = new ImgDataConverter(invWT, 0);

        // **** Inverse component transformation ****
        InvCompTransf ictransf = new InvCompTransf(converter, decSpec, depth,
                pl);

        // **** Color space mapping ****
        ColorSpace csMap;
        BlkImgDataSrc color;
        BlkImgDataSrc palettized;
        BlkImgDataSrc resampled;
        BlkImgDataSrc channels;

        if (ff.JP2FFUsed && pl.getParameter("nocolorspace").equals("off")) {
            try {
                csMap = new ColorSpace(in, hd, pl);
                channels = hd.createChannelDefinitionMapper(ictransf, csMap);
                resampled = hd.createResampler(channels, csMap);
                palettized = hd.createPalettizedColorSpaceMapper(resampled, csMap);
                color = hd.createColorSpaceMapper(palettized, csMap);
            } catch (ColorSpaceException | ICCProfileException ex) {
                throw new J2kException(ex);
            }
        } else { // Skip colorspace mapping
            color = ictransf;
        }

        // This is the last image in the decoding chain
        BlkImgDataSrc decodedImage = color;
        if (color == null) {
            decodedImage = ictransf;
        }
        return decodedImage;
    }
}
