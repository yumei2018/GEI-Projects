/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author hdunsford
 */
public class VariableLengthRecord {

    //<editor-fold defaultstate="collapsed" desc="Fields">

    private int reserved; // 2 bytes
    private String userId; // 16 bytes
    private int recordId; // 2 bytes
    // Record length after header
    private int lengthAfterHeader; // 2 bytes
    private String description; // 32 bytes

    private String content;
    //</editor-fold>

    /**
     * Creates a new instance of the VariableLengthRecord class.
     */
    public VariableLengthRecord(){
  
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  

    public void read(DataInputStream stream) throws IOException{
        ReadHelper rh = new ReadHelper(stream);
        reserved = rh.readUShort();
        userId = rh.readChars(16);
        recordId = rh.readUShort();
        lengthAfterHeader = rh.readUShort();
        description = rh.readChars(32);
        content = rh.readChars(lengthAfterHeader);
    }
    

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
