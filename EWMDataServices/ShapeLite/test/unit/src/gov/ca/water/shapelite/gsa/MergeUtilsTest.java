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
package gov.ca.water.shapelite.gsa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MergeUtilsTest {

  public MergeUtilsTest() {
  }

  /**
   * Test of merge method, of class MergeUtils.
   */
  @Test
  public void testMerge_12() throws Exception {
    System.out.println("merge");
    FileInputStream stream1 = new FileInputStream("D:\\Data\\GSA2\\Polygon 1.zip");
    FileInputStream stream2 = new FileInputStream("D:\\Data\\GSA2\\Polygon 2.zip");
    List<InputStream> inputStreams = new ArrayList<>();
    inputStreams.add(stream1);
    inputStreams.add(stream2);
    OutputStream mergedOutput = new FileOutputStream("D:\\Data\\GSA2\\Merge12.zip");
    String gsaID = "1";
    String gsaName = "Fish";
    MergeUtils.merge(inputStreams, mergedOutput, gsaID, gsaName);

  }

  /**
   * Test of merge method, of class MergeUtils.
   */
  @Test
  public void testMerge_14() throws Exception {
    System.out.println("merge");
    FileInputStream stream1 = new FileInputStream("D:\\Data\\GSA2\\Polygon 1.zip");
    FileInputStream stream2 = new FileInputStream("D:\\Data\\GSA2\\Polygon 4.zip");
    List<InputStream> inputStreams = new ArrayList<>();
    inputStreams.add(stream1);
    inputStreams.add(stream2);
    OutputStream mergedOutput = new FileOutputStream("D:\\Data\\GSA2\\Merge14.zip");
    String gsaID = "1";
    String gsaName = "Fish";
    MergeUtils.merge(inputStreams, mergedOutput, gsaID, gsaName);

  }



}
