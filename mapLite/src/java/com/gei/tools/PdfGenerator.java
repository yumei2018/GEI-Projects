/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author ileung
 */
//@Controller
//@RequestMapping("/generatePDF")
public class PdfGenerator{
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//    @RequestMapping("/gen")
    public String generatePDF(String nameStr, String titleStr, String agencyStr, Date date) throws IOException{        
        PdfReader reader = new PdfReader(this.getClass().getResourceAsStream("resources/Data_Sharing_Disclaimer_DWR.pdf"));               
        String dateStr = sdf.format(date);        
        try {
            int numPages = reader.getNumberOfPages();            
            PdfImportedPage page;
            PdfCopy.PageStamp stamp;
            
            Document document = new Document();
            PdfCopy tmpCopy = new PdfCopy(document, new FileOutputStream("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\test.pdf"));
            
            document.open();
            List<HashMap<String, Object>> bookmarks = SimpleBookmark.getBookmark(reader);
            for (int iPage = 0; iPage < numPages; iPage++ ) {
                page = tmpCopy.getImportedPage(reader, (iPage+1));
                if (iPage == 4) {
                  stamp = tmpCopy.createPageStamp(page);
                  // add Time Stamp
                  Font txtFont = 
                          FontFactory.getFont("Arial", 13.0f, Font.NORMAL, Color.black);
                  Phrase namePhrase = new Phrase(nameStr,txtFont);
                  Phrase titlePhrase = new Phrase(titleStr,txtFont); 
                  Phrase agencyPhrase = new Phrase(agencyStr,txtFont); 
                  Phrase datePhrase = new Phrase(dateStr,txtFont);                   
                  ColumnText.showTextAligned(stamp.getOverContent(), Element.ALIGN_LEFT, namePhrase, 87.0f, 418.0f, 0);
                  ColumnText.showTextAligned(stamp.getOverContent(), Element.ALIGN_LEFT, titlePhrase, 87.0f, 390.0f, 0);
                  ColumnText.showTextAligned(stamp.getOverContent(), Element.ALIGN_LEFT, agencyPhrase, 87.0f, 362.0f, 0);
                  ColumnText.showTextAligned(stamp.getOverContent(), Element.ALIGN_LEFT, datePhrase, 87.0f, 306.0f, 0);
                  stamp.alterContents();
                }
                tmpCopy.addPage(page);
            }
            if ((bookmarks != null) && (!bookmarks.isEmpty())) {
              tmpCopy.setOutlines(bookmarks);
            }
            document.close();
        } catch (DocumentException | IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {            
            reader.close();
        }
        return "C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\test.pdf";
    }
    
}
