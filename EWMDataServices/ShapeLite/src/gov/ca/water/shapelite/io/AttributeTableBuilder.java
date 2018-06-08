/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * This class can be used similarly to the writer, except that it works like the
 * ShapefileBuilder, adding records and then updating the header at the end.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableBuilder {

    private List<Field> fields;
    private int count;
    private Writer writer;
    private FileOutputStream dbfStream;
    private boolean started = false;
    private String dbf;
   
    private boolean fixedFields;
    


    /**
     * Writes the header content of the dbf file.
     *
     * @param dbfStream The stream to write to.
     * @param numRecords The integer number of records to write.
     */
    private void writeHeader(Writer writer) throws IOException {

        // write the output file type.
        int code = 0x03;
        writer.writeByte((byte) code);

        Calendar calendar = Calendar.getInstance();

        writer.writeByte((byte) (calendar.get(Calendar.YEAR) - 1900));
        writer.writeByte((byte) calendar.get(Calendar.MONTH) + 1);
        writer.writeByte((byte) calendar.get(Calendar.DAY_OF_MONTH));

        // write the number of records in the datafile.
        writer.write(count);

        // write the length of the header structure.
        writer.writeShort(32 + 32 * fields.size() + 1); // 32 + 32 * numColumns + 1
        int recordLength = 1; // delete character
        for (Field field : fields) {
            int len = field.getLength();
            if (len > 198) {
                len = 198;
            }
            recordLength += len;
        }

        // write the length of a record
        writer.writeShort(recordLength);

        // write the reserved bytes in the header
        writer.writeBlank(20);

        int offset = 0;
        // write all of the header records
        for (Field currentField : fields) {
            // write the field name
            writer.writeBlank(currentField.getName(), 10);

            // Always terminate the string with a null.  Even though
            // 11 characters are used for the string, the last character must be null.
            writer.writeBlank(1);

            // write the field type
            writer.writeBlank(currentField.getType(), 1);

            // write the field data address, offset from the start of the record.
            writer.write(offset);

            // write the length of the field.
            writer.writeByte(currentField.getLength());

            offset += currentField.getLength();

            // write the decimal count.
            writer.writeByte(currentField.getDecimal());

            // write the reserved bytes.
            writer.writeBlank(14);
        }
        // write the end of the field definitions marker
        writer.writeByte((byte) 0x0D);

    }

    public void open(String filename) throws FileNotFoundException {
        dbf = filename;
        this.dbfStream = new FileOutputStream(filename);
        writer = new Writer(dbfStream);
    }
    
    private int getHeaderSize(){
        return 32 + 32 * fields.size() + 1;
    }

    /**
     * If the fields are null, then the first feature will automatically 
     * define all the fields as string fields length 25.
     * @param feature 
     */
    public void add(Feature feature) {
        HashMap<String, String> attributes = feature.getAttributes();
        if (fields == null) {
            fields = new ArrayList<>();
        }
        if(!started){
            if(!fixedFields){
                for (String key : attributes.keySet()) {
                    Field fld = new Field(key, FieldType.Character, 50);
                    fields.add(fld);
                }
            }
            
            writer.writeSpace(getHeaderSize());
            started = true;
        }
        writer.writeSpace(" ", 1); // a "*" would signify the row was deleted.
        for (Field field : fields) {
            int len = field.getLength();
            if (len > 198) {
                len = 198;
            }
            if (attributes.containsKey(field.getName())) {
                writer.writeSpace(attributes.get(field.getName()), len);
            } else {
                writer.writeSpace(len);
            }
        }
        count++;
    }

    /**
     * This method will first close the file and then finalize the header
     * by writing over the first part of the file.
     * @throws IOException 
     */
    public void close() throws IOException {
        writer.writeByte(26);
        writer.flush();
        writer.close();
        RandomAccessFile raf = new RandomAccessFile(dbf, "rw");
        raf.seek(0);
        byte[] bt = new byte[getHeaderSize()];
        Writer w = new Writer(bt);
        writeHeader(w);
        w.flush();
        raf.write(bt);
        w.flush();
        raf.close();
    }

    /**
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * If this is set to true, then this will prevent dynamically added fields 
     * based on the first feature.  Otherwise, any attributes in the first 
     * feature will be added to the field list as Strings of length 50.
     * @return the fixedFields
     */
    public boolean hasFixedFields() {
        return fixedFields;
    }

    /**
     * @param fixedFields the fixedFields to set
     */
    public void setFixedFields(boolean fixedFields) {
        this.fixedFields = fixedFields;
    }

    
}
