/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.LanguageDriver;
import gov.ca.water.shapelite.Optional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableScannerTest {

  /**
   * An error logger for the AttributeTableScannerTest class.
   */
  private static final Logger LOGGER = Logger.getLogger("AttributeTableScannerTest");

  /**
   * A date formatter that uses DBF format to parse or format a date.
   */
  private static final SimpleDateFormat DBF_DATE = new SimpleDateFormat("yyyyMMdd");

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  private File testFile;

  private static final String[] ROW1
      = new String[]{"Test", "10", "1.1", "Y", "19970102"};

  private static final String[] ROW2
      = new String[]{"Test2", "20", "2.2", "N", "20000102"};

  public AttributeTableScannerTest() {
  }

  /**
   * Creates a temporary test file to read.
   *
   * @throws IOException
   */
  private void createTable() {
    try {
      FeatureSet fs = new FeatureSet();
      fs.getFields().add(new Field("TestString", FieldType.Character, 25));
      fs.getFields().add(new Field("TestInt", 10, 0));
      fs.getFields().add(new Field("TestDouble", 10, 3));
      fs.getFields().add(new Field("TestLogic", FieldType.Logical, 1));
      fs.getFields().add(new Field("TestDate", FieldType.Date, 10));
      Feature f = new Feature();
      f.getAttributes().put("TestString", "Test");
      f.getAttributes().put("TestInt", "10");
      f.getAttributes().put("TestDouble", "1.1");
      f.getAttributes().put("TestLogic", "Y");
      f.getAttributes().put("TestDate", "19970102");
      fs.getFeatures().add(f);
      Feature f2 = new Feature();
      f2.getAttributes().put("TestString", "Test2");
      f2.getAttributes().put("TestInt", "20");
      f2.getAttributes().put("TestDouble", "2.2");
      f2.getAttributes().put("TestLogic", "N");
      f2.getAttributes().put("TestDate", "20000102");
      fs.getFeatures().add(f2);
      AttributeTableWriter writer = new AttributeTableWriter();
      testFile = testFolder.newFile("AttributeScannerTest.dbf");
      try (FileOutputStream stream = new FileOutputStream(testFile)) {

        writer.write(stream, fs);
      } catch (IOException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    } catch (IOException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  @Before
  public void setup() {
    if (testFile == null) {
      createTable();
    }
  }

  /**
   * Test of getFieldItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldItemsInt() throws FileNotFoundException {
    System.out.println("getFieldItems");
    int fieldIndex = 0;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);

    List<Object> result = instance.getFieldItems(fieldIndex);
    assertEquals("Test", result.get(0));
    assertEquals("Test2", result.get(1));
    assertEquals(2, result.size());
  }

  /**
   * Test of getFieldItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldItemsIntMissing() throws FileNotFoundException {
    System.out.println("getFieldItems");
    int fieldIndex = 9;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    boolean thrown = false;
    try {
      instance.getFieldItems(fieldIndex);
    } catch (IndexOutOfBoundsException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  /**
   * Test of getFieldItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldItemsString() throws FileNotFoundException {
    System.out.println("getFieldItems");
    String fieldIndex = "TestInt";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);

    List<Object> result = instance.getFieldItems(fieldIndex);
    assertEquals((long) 10, result.get(0));
    assertEquals((long) 20, result.get(1));
    assertEquals(2, result.size());
  }

  /**
   * Test of getFieldItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldItemsStringMissing() throws FileNotFoundException {
    System.out.println("getFieldItems");
    String fieldIndex = "TestMissing";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    boolean thrown = false;
    try {
      instance.getFieldItems(fieldIndex);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  /**
   * Test of getFieldStrings method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldStringsString() throws FileNotFoundException {
    System.out.println("getFieldItems");
    String fieldIndex = "TestDouble";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);

    List<String> result = instance.getFieldStrings(fieldIndex);
    assertEquals("1.1", result.get(0));
    assertEquals("2.2", result.get(1));
    assertEquals(2, result.size());
  }

  /**
   * Test of getFieldStrings method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldStringsStringMissing() throws FileNotFoundException {
    System.out.println("getFieldItems");
    String fieldIndex = "TestMissing";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    boolean expResult = true;
    boolean thrown = false;
    try {
      List<String> result = instance.getFieldStrings(fieldIndex);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);

  }

  /**
   * Test of getField method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetField() throws FileNotFoundException {
    System.out.println("getField");
    String fieldName = "TestDouble";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    Optional<Field> result = instance.getField(fieldName);
    Field field = result.get();
    assertEquals("TestDouble", field.getName());
    assertEquals(10, field.getLength());
    assertEquals(3, field.getDecimal());
    assertEquals("N", field.getType());
  }

  /**
   * Test of getField method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldMissing() throws FileNotFoundException {
    System.out.println("getField");
    String fieldName = "TestMissing";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    Optional<Field> result = instance.getField(fieldName);
    assertEquals(false, result.isPresent());
  }

  /**
   * Test of getFieldIndex method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldIndex() throws FileNotFoundException {
    System.out.println("getField");
    System.out.println("getField");
    String fieldName = "TestDouble";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    int index = instance.getFieldIndex(fieldName);
    assertEquals(2, index);
  }

  /**
   * Test of getFieldIndex method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldIndexMissing() throws FileNotFoundException {
    System.out.println("getField");
    System.out.println("getField");
    String fieldName = "Missing";
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    int index = instance.getFieldIndex(fieldName);
    assertEquals(-1, index);
  }

  /**
   * Test of getFieldStrings method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFieldStringsInt() throws FileNotFoundException {
    System.out.println("getFieldStrings");
    int fieldIndex = 0;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    List<String> result = instance.getFieldStrings(fieldIndex);
    assertEquals("Test", result.get(0));
    assertEquals("Test2", result.get(1));
    assertEquals(2, result.size());
  }

  /**
   * Test of getRow method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetRow() throws FileNotFoundException {
    System.out.println("getRow");
    int row = 0;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    String[] expResult = ROW1;
    String[] result = instance.getRow(row);
    for (int i = 0; i < expResult.length; i++) {
      assertEquals(expResult[i], result[i].trim());
    }
  }

  /**
   * Test of getRows method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetRowsStartCount() throws FileNotFoundException {
    System.out.println("getRowsStartCount");
    int start = 1;
    int numRows = 1;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    String[] expResult = ROW2;
    List<String[]> result = instance.getRows(start, numRows);
    for (int i = 0; i < expResult.length; i++) {
      assertEquals(expResult[i], result.get(0)[i].trim());
    }

  }

  /**
   * Test of getRows method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetRows() throws FileNotFoundException {
    System.out.println("getRows");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    List<String[]> result = instance.getRows();
    assertEquals(2, result.size());
    for (int i = 0; i < ROW1.length; i++) {
      assertEquals(ROW1[i], result.get(0)[i].trim());
    }
    for (int i = 0; i < ROW2.length; i++) {
      assertEquals(ROW2[i], result.get(1)[i].trim());
    }
  }

  /**
   * Test of getNextRow method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetNextRow() throws FileNotFoundException {
    System.out.println("getNextRow");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    String[] expResult = ROW1;
    Optional<String[]> result = instance.getNextRow();
    assertArrayEquals(expResult, result.get());
  }

  /**
   * Test of getRowItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException If the test dbf file was not found.
   * @throws java.text.ParseException If the test date format cannot be
   * converted to a date.
   */
  @Test
  public void testGetRowItemsStartCount() throws FileNotFoundException, ParseException {
    System.out.println("getRowItems");
    int start = 0;
    int numRows = 1;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    List<Object[]> result = instance.getRowItems(start, numRows);
    Object[] row = result.get(0);
    assertRowEquals(ROW1, row);
  }

  /**
   * Tests the objects in the row to ensure that they match the expected row.
   *
   * @param rawRow The String raw row to compare against.
   * @param row The object row to test.
   * @throws ParseException If the date could not be parsed.
   */
  private void assertRowEquals(String[] rawRow, Object[] row) throws ParseException {
    assertEquals(rawRow[0], row[0]);
    assertEquals(Long.parseLong(rawRow[1]), row[1]);
    assertEquals(Double.parseDouble(rawRow[2]), row[2]);
    boolean val = false;
    if ("Y".equals(rawRow[3])) {
      val = true;
    }
    assertEquals(val, row[3]);
    assertEquals(DBF_DATE.parse(rawRow[4]), row[4]);
  }

  /**
   * Test of getRowItems method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException If the test dbf file was not found.
   * @throws java.text.ParseException If the test date format cannot be
   * converted to a date.
   */
  @Test
  public void testGetRowItems() throws FileNotFoundException, ParseException {
    System.out.println("getRowItems");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    List<Object[]> result = instance.getRowItems();
    Object[] row = result.get(0);
    Object[] row2 = result.get(1);
    assertRowEquals(ROW1, row);
    assertRowEquals(ROW2, row2);
  }

  /**
   * Test of getCode method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetCode() throws FileNotFoundException {
    System.out.println("getCode");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    byte expResult = (byte) 0x03;
    byte[] result = instance.getCode();
    assertEquals(expResult, result[0]);
  }

  /**
   * Test of setCode method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testSetCode() throws FileNotFoundException {
    System.out.println("setCode");
    byte[] code = new byte[2];
    code[0] = (byte) 12;
    code[1] = (byte) 13;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    instance.setCode(code);
    byte[] test = instance.getCode();
    assertArrayEquals(code, test);
  }

  /**
   * Test of getFields method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException
   */
  @Test
  public void testGetFields() throws FileNotFoundException {
    System.out.println("getFields");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    assertEquals(5, instance.getFields().size());
    String expName = "TestString";
    assertEquals(expName, instance.getFields().get(0).getName());
  }

  /**
   * Test of getFilename method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException if the test stream can't be read.
   */
  @Test
  public void testGetFilename() throws FileNotFoundException {
    System.out.println("getFilename");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    String expResult = null;
    String result = instance.getFilename();
    assertEquals(expResult, result);
    expResult = "TestName";
    instance.setFilename(expResult);
    assertEquals(expResult, instance.getFilename());
  }

  /**
   * Test of getLanguageDriver method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException if the test stream can't be read.
   */
  @Test
  public void testGetLanguageDriver() throws FileNotFoundException {
    System.out.println("getLanguageDriver");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    LanguageDriver result = instance.getLanguageDriver();
    LanguageDriver expResult = LanguageDriver.OEM;
    assertEquals(expResult, result);
    expResult = LanguageDriver.ANSI;
    instance.setLanguageDriver(expResult);
    assertEquals(expResult, instance.getLanguageDriver());

  }

  /**
   * Test of getNumRecords method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException if the test stream can't be read.
   */
  @Test
  public void testGetNumRecords() throws FileNotFoundException {
    System.out.println("getNumRecords");
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    int expResult = 2;
    int result = instance.getNumRecords();
    assertEquals(expResult, result);
  }

  /**
   * Test of setNumRecords method, of class AttributeTableScanner.
   *
   * @throws java.io.FileNotFoundException if the test stream can't be read.
   */
  @Test
  public void testSetNumRecords() throws FileNotFoundException {
    System.out.println("setNumRecords");
    int numRecords = 3;
    FileInputStream inputStream = new FileInputStream(testFile);
    AttributeTableScanner instance = new AttributeTableScanner(inputStream);
    instance.setNumRecords(numRecords);
    assertEquals(numRecords, instance.getNumRecords());
  }

}
