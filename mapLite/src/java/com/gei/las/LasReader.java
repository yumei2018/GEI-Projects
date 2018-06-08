/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author hdunsford
 */
public class LasReader {

    //<editor-fold defaultstate="collapsed" desc="Fields">    
    private LasHeader header;
    private List<VariableLengthRecord> variableLengthRecords;
    private String filename;
    
    private final List<Integer> x = new ArrayList<>();
    private final List<Integer> y = new ArrayList<>();
    private final List<FilesOffsetNode> allList = new ArrayList<>();




    //</editor-fold>

    /**
     * Creates a new instance of the LasReader class.
     */
    public LasReader(){
        variableLengthRecords = new ArrayList<>();
//        a = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Opens the specified file path for reading and reads the header.
     * @param path 
     */
    public void open(String path){
        filename = path;
        try(DataInputStream stream = new DataInputStream(new FileInputStream(filename))){
            header = new LasHeader();
            header.readHeader(stream);    
            for(int i = 0; i < header.getVariableLengthRecordCount(); i++){
              VariableLengthRecord rec = new VariableLengthRecord();
              rec.read(stream);
              variableLengthRecords.add(rec);
            }
//            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\ileung\\Documents\\Test2\\header.txt"),true));
//            bw.write(header.getMinX() + "\t" + header.getMaxX() + "\t" + header.getMinY() + "\t" + header.getMaxY());
//            bw.newLine();
//            bw.close();
        } catch (IOException | LasReadException ex) {
            Logger.getLogger(LasReader.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }
    
    /**
     * Attempts to get all the points as a single return. 
     * @return a list of PointRecord values that only have x, y and z data.
     * @throws LasReadException 
     */
    public List<PointRecord> getPointsOnly() throws LasReadException{
        return getPointsOnly(0, header.getPointRecordCount());
    }
    
    public List<PointRecord> getPointsOnly(long startIndex) throws LasReadException {
        return getPointsOnly(startIndex, header.getPointRecordCount());
    }
    /**
     * Reads just the X, Y and Z values for the points, ignoring the other metadata.
     * @param startIndex start index, inclusive
     * @param endIndex end index, exclusive
     * @return 
     * @throws LasReadException 
     */
    public List<PointRecord> getPointsOnly(long startIndex, long endIndex) throws LasReadException{
        return getPointsOnlyTest(startIndex, endIndex, 0);
    }
    
    /**
     * this is a new method for reading the las file using a file channel and a random access file and a 
     * mappedbytebuffer
     * 
     * @param startIndex
     * @param endIndex
     * @param skip
     * @return
     * @throws LasReadException 
     */
    
    public List<PointRecord> getPointsOnlyTest(long startIndex, long endIndex, long skip) throws LasReadException {
        if(this.filename == null){
            throw new LasReadException("The file should be open first before reading.");
        } 
        List<PointRecord> results = new ArrayList<>();
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            FileChannel inChannel = file.getChannel();
            long readAt = header.getOffsetToPointData() + header.getPointDataRecordLength()*startIndex;
            long width = (endIndex - startIndex) * header.getPointDataRecordLength();
            int otherStuff = header.getPointDataRecordLength() - 12 - 3 - 1; //2 bytes applocated for Intensity 1byte for number of returns/ scan direction/ edge of flight line and 1 byte fir classification             
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, readAt, width );
            buffer.load();
            int nGet, skipStuff;
            byte[] bArrayX = new byte[4], bArrayY = new byte[4], bArrayZ = new byte[4], uselessMid = new byte[3], classification = new byte[1], useless = new byte[otherStuff];
            long checkSum = 0L;
            while(buffer.hasRemaining()) {
                nGet = Math.min(buffer.remaining(), 4);
                skipStuff = Math.min(buffer.remaining(), otherStuff);
                buffer.get(bArrayX, 0, nGet);
                buffer.get(bArrayY, 0, nGet);
                buffer.get(bArrayZ, 0, nGet);
                buffer.get(uselessMid, 0, 3);
                buffer.get(classification, 0 , 1);
                buffer.get(useless, 0,skipStuff);
                Integer type = ((Byte)classification[0]).intValue();
                if(type == 2 || type > 12 || type == 9) {
                    ArrayUtils.reverse(bArrayX);
                    ArrayUtils.reverse(bArrayY);
                    ArrayUtils.reverse(bArrayZ);                    
                    Integer testX = ByteBuffer.wrap(bArrayX).getInt();
                    Integer testY = ByteBuffer.wrap(bArrayY).getInt();
                    Integer testZ = ByteBuffer.wrap(bArrayZ).getInt();  
                    PointRecord rec = new PointRecord();
                    rec.setX(testX);
                    rec.setY(testY);
                    rec.setZ(testZ);
                    results.add(rec);
                }
            }
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return results;
    }
    /**
     * Reads just the X, Y and Z values for the points, ignoring the other metadata.
     * @param startIndex start index, inclusive
     * @param endIndex end index, exclusive
     * @param skip the number of records to skip over when reading.
     * @return 
     * @throws LasReadException 
     */
    public List<PointRecord> getPointsOnly(long startIndex, long endIndex, long skip) throws LasReadException{
        if(this.filename == null){
            throw new LasReadException("The file should be open first before reading.");
        }        
        List<PointRecord> results = new ArrayList<>();
        try(DataInputStream stream = new DataInputStream(new FileInputStream(filename))){
            ReadHelper rh = new ReadHelper(stream);
            stream.skip(header.getOffsetToPointData());
            long offset = header.getPointDataRecordLength()*startIndex;
            if(offset > 0){
                stream.skip(offset);
            }
            int otherStuff = header.getPointDataRecordLength() - 12;
            long skipSize = skip*header.getPointDataRecordLength();
            int skipLength = 1;
            if(skip != 0) {
                skipLength = (int) skip;
            }
            for(long i = startIndex; i < endIndex; i+=skipLength){
                PointRecord rec = new PointRecord();
                rec.setX(rh.readInt());
                rec.setY(rh.readInt());
                rec.setZ(rh.readInt());
                stream.skip(otherStuff);
                if(skipSize > 0){
                    stream.skip(skipSize);
                }
                results.add(rec);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LasReader.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return results;
    }           
    /**
     * Attempts to get all the points as a single return. 
     * @return a list of PointRecord values that only have x, y and z data.
     * @throws LasReadException 
     */
    public List<FilesOffsetNode> getPointsOnly3() throws LasReadException{
        return getPointsOnly3(0, header.getPointRecordCount());
    }
    
    /**
     * Reads just the X, Y and Z values for the points, ignoring the other metadata.
     * @param startIndex start index, inclusive
     * @param endIndex end index, exclusive
     * @return 
     * @throws LasReadException 
     */
    public List<FilesOffsetNode> getPointsOnly3(long startIndex, long endIndex) throws LasReadException{
        return getPointsOnly3(startIndex, endIndex, 0);
    }
    
    /**
     * Reads just the X, Y and Z values for the points, ignoring the other metadata.
     * @param startIndex start index, inclusive
     * @param endIndex end index, exclusive
     * @param skip the number of records to skip over when reading.
     * @return 
     * @throws LasReadException 
     */
    public List<FilesOffsetNode> getPointsOnly3(long startIndex, long endIndex, long skip) throws LasReadException{
        if(this.filename == null){
            throw new LasReadException("The file should be open first before reading.");
        }
        List<FilesOffsetNode> results = new ArrayList<>();
//        try(DataInputStream stream = new DataInputStream(new FileInputStream(filename))){
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            FileChannel inChannel = file.getChannel();
            long readAt = header.getOffsetToPointData() + header.getPointDataRecordLength()*startIndex;   
            long width = (endIndex - startIndex) * header.getPointDataRecordLength();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, readAt, width);
            buffer.load();
//            ReadHelper rh = new ReadHelper(stream);
//            stream.skip(header.getOffsetToPointData());
//            long offset = header.getPointDataRecordLength()*startIndex;
//            if(offset > 0){
//                stream.skip(offset);
//            }
            int otherStuff = header.getPointDataRecordLength() - 12;
//            long skipSize = skip*header.getPointDataRecordLength();
//            int skipLength = 1;
//            if(skip != 0) {
//                skipLength = (int) skip;
//            }
            int nGet, skipStuff;
            byte[] bArrayX = new byte[4], bArrayY = new byte[4], bArrayZ = new byte[4], useless = new byte[otherStuff];
            long checkSum = 0L;
            
            Integer ymin = Integer.MAX_VALUE, ymax =  Integer.MIN_VALUE, xmin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE;
            //Test variables
            boolean first = true;
            Integer prevYMin = 0;
            Integer xMinBand = Integer.MAX_VALUE, xMaxBand = Integer.MIN_VALUE;
            //end test
            Integer lastx = 0;
            Long totalOffset = (long) 0;
            List<XBlock> xblock = new ArrayList<>();
            while(buffer.hasRemaining()){
//            for(long i = startIndex; i < endIndex; i+=skipLength){
                nGet = Math.min(buffer.remaining(), 4);
                skipStuff = Math.min(buffer.remaining(), otherStuff);
                buffer.get(bArrayX, 0, nGet);
                buffer.get(bArrayY, 0, nGet);
                buffer.get(bArrayZ, 0, nGet);
                buffer.get(useless, 0,skipStuff);
                ArrayUtils.reverse(bArrayX);
                ArrayUtils.reverse(bArrayY);
                ArrayUtils.reverse(bArrayZ);
                Integer testX = ByteBuffer.wrap(bArrayX).getInt();
                Integer testY = ByteBuffer.wrap(bArrayY).getInt();
                Integer testZ = ByteBuffer.wrap(bArrayZ).getInt();  
                PointRecord rec = new PointRecord();
                rec.setX(testX);
                rec.setY(testY);
                rec.setZ(testZ);
//                stream.skip(otherStuff);
                FilesOffsetNode block;
                if( ((rec.getX() - lastx) < -300000) && (ymin != Integer.MAX_VALUE || ymax != Integer.MIN_VALUE)) {
                    if(!first) {                                  
//                        int previous = results.size() -1;
                        ymax = prevYMin;
                        prevYMin = ymin;
                        block = new FilesOffsetNode(ymin, ymax, xMinBand, xMaxBand, totalOffset, xblock);  
                        writeData(block);
                    } else {
                        first = false;
                        prevYMin = ymin;
                        block = new FilesOffsetNode(ymin, ymax, xMinBand, xMaxBand, totalOffset, xblock);                        
                        writeData(block);
                    }   
                    xmin = Integer.MAX_VALUE; 
                    xmax = Integer.MIN_VALUE;
                    xMinBand = Integer.MAX_VALUE; 
                    xMaxBand = Integer.MIN_VALUE;
//                    results.add(block);
                    xblock = new ArrayList<>();
                    System.gc();
                }
                lastx = rec.getX();
                //xmin and max inside each band
                if(rec.getY() > ymax) {
                    ymax = rec.getY();
                }
                if(rec.getY() < ymin) {
                    ymin = rec.getY();
                }
                if(rec.getX() > xmax) {
                    xmax = rec.getX();
                }
                if(rec.getX() < xmin) {
                    xmin = rec.getX();
                }
                //xmin xmax for each band
                if(rec.getX() > xMaxBand) {
                    xMaxBand = rec.getX();
                }
                if(rec.getX() < xMinBand) {
                    xMinBand = rec.getX();
                }
                if(xmax - xmin > 10000) {
                    xblock.add(new XBlock(xmin, xmax, totalOffset, filename));
                    xmin = Integer.MAX_VALUE;
                    xmax = Integer.MIN_VALUE;
                }
                
                if(!buffer.hasRemaining()) {
                    block = new FilesOffsetNode(ymin, ymax, xMinBand, xMaxBand, totalOffset, xblock);
                    writeData(block);
//                    results.add(block);
                    xblock = new ArrayList<>();
                }
//                if(skipSize > 0){
//                    stream.skip(skipSize);
//                }
                totalOffset++;
//                results.add(rec);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LasReader.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        getAllList().addAll(results);
        return results;
    }
    
    private static void writeData(FilesOffsetNode node) {         
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\ileung\\Documents\\Test2\\offset.txt"),true))) {            
            bw.write(node.getYmin() + "," + node.getYmax() + "," + node.getXmin() + "," + node.getXmax());
            bw.newLine();
            for(int i = 0; i < node.getxBlock().size(); i++) {
                XBlock block = node.getxBlock().get(i);
                bw.write("\t");
                bw.write(block.getXmin() + "," + block.getXmax() + "," + block.getOffset() + "," + block.getFileName());
                bw.newLine();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }                                         
    }
    private static void writeData(XBlock block) {        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\ileung\\Documents\\Test2\\offset.txt"),true))) {            
            bw.write("\t");
            bw.write(block.getXmin() + "," + block.getXmax() + "," + block.getOffset() + "," + block.getFileName());
            bw.newLine();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
     /**
     * @return the x
     */
    public List<Integer> getX() {
        return x;
    }

    /**
     * @return the y
     */
    public List<Integer> getY() {
        return y;
    }
    /**
     * @return the allRec
     */
//    public List<PointRecord> getAllRec() {
//        return allRec;
//    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    

    /**
     * @return the header
     */
    public LasHeader getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(LasHeader header) {
        this.header = header;
    }

    /**
     * @return the variableLengthRecords
     */
    public List<VariableLengthRecord> getVariableLengthRecords() {
        return variableLengthRecords;
    }

    /**
     * @param variableLengthRecords the variableLengthRecords to set
     */
    public void setVariableLengthRecords(List<VariableLengthRecord> variableLengthRecords) {
        this.variableLengthRecords = variableLengthRecords;
    }

    //</editor-fold>

    /**
     * @return the allList
     */
    public List<FilesOffsetNode> getAllList() {
        return allList;
    }

   

  
}
