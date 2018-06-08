/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.gei.entities.AdminReview;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openide.util.Exceptions;

/**
 *
 * @author ileung
 */
public class ExcelUpdater {
    
    
    public static void updateExcel(AdminReview adm){
        //Missing imports
        
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        try {
////            File file = new File("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\dataTrackingTest.xlsx");
////            OPCPackage pkg = OPCPackage.open(file);
////            XSSFWorkbook wb = new XSSFWorkbook(pkg);
//            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\dataTrackingTest.xlsx"));
//            XSSFSheet sheet = wb.getSheetAt(0);           
//            XSSFRow updateRow = sheet.createRow(sheet.getLastRowNum()+1);
//            updateRow.createCell(11).setCellValue(adm.getUserRequest().getRequestId());
//            updateRow.createCell(12).setCellValue(adm.getUserRequest().getFirstName() + " " + adm.getUserRequest().getLastName());
//            updateRow.createCell(13).setCellValue(adm.getUserRequest().getPhoneNumber());
////            updateRow.createCell(14).setCellValue(adm.getUserRequest().);
////            updateRow.createCell(15).setCellValue(adm.getUserRequest().getRequestId());
////            updateRow.createCell(16).setCellValue(adm.getUserRequest().getRequestId()); 
////            updateRow.createCell(17).setCellValue(adm.getUserRequest().getRequestId()); 
////            updateRow.createCell(18).setCellValue(adm.getUserRequest().getRequestId()); lidar vs CVFED lidar?
////            updateRow.createCell(19).setCellValue(adm.getUserRequest().getRequestId()); signed release form date = request date?
//            updateRow.createCell(20).setCellValue(sdf.format(adm.getUserRequest().getRequestDate()));
////            updateRow.createCell(21).setCellValue(adm.getUserRequest().getRequestId());
////            updateRow.createCell(22).setCellValue(adm.getUserRequest().getRequestId());
//            updateRow.createCell(23).setCellValue(parseClob(adm.getUserRequest().getReason()));            
////            updateRow.createCell(24).setCellValue(adm.getUserRequest().getRequestId());
////            updateRow.createCell(25).setCellValue(adm.getUserRequest().getRequestId());
////            updateRow.createCell(26).setCellValue(adm.getUserRequest().getRequestId());
//            FileOutputStream out = new FileOutputStream("C:\\\\Users\\\\ileung\\\\Documents\\\\Test2\\\\Files\\\\export\\\\dataTrackingTest.xlsx");
//            wb.write(out);            
//            wb.close();
//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//        }        
    }
    
    /**
     * This parses the clob stored in the database and returns a Sring
     * @param clob
     * @return 
     */
    private static String parseClob(Clob clob){
        if(clob == null) {
            return "";
        }
        try {
            BufferedReader br = new BufferedReader(clob.getCharacterStream());
            StringBuilder sb = new StringBuilder();
            String line;
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString().trim();
        } catch (IOException | SQLException ex) {
            Exceptions.printStackTrace(ex);            
            return "";
        }
    }
    
}
