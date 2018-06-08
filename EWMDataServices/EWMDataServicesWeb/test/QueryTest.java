
import entity.core.util.ExceptionUtil;
import gov.ca.water.entity.ewm.collection.StationCollection;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schen
 */
public class QueryTest {
  
  public final String empty = "";
  public final String fullExtent = "[[-125.14030082759544,32.52828384187941],[-113.95621879634841,42.293559346676304]]";
  
  public final String query1 = "keyword=&startDate=&endDate=&minWellDepth=&maxWellDepth=&acceptNullDepth=false&wellUseType=&extent=[[-119.69108207759687,37.04640367138529],[-118.05411918697233,38.51378313397171]]";
        
  @Test
  public void createImageTest(){
    JSONArray queryResult = new JSONArray();
    try {
      long startTime = System.currentTimeMillis();
      StationCollection stations = new StationCollection();
      String extent = "[[-125.14030082759544,32.52828384187941],[-113.95621879634841,42.293559346676304]]"; //full extent
      String keyword = "";
      String startDate= "01/01/2017";
      String endDate= "07/01/2017";
      String minWellDepth = "";
      String maxWellDepth = "";
      Boolean acceptNullDepth = false;
      String wellUseType = "";
      queryResult = stations.query(keyword, startDate, endDate, minWellDepth, maxWellDepth, acceptNullDepth, wellUseType, fullExtent);
      Integer width = 1000;
      Integer height = 1000;
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D ig2 = bi.createGraphics();
      JSONArray extentArray = new JSONArray(extent);
      Double minLon = extentArray.getJSONArray(0).getDouble(0);
      Double maxLon = extentArray.getJSONArray(1).getDouble(0);
      Double minLat = extentArray.getJSONArray(0).getDouble(1);
      Double maxLat = extentArray.getJSONArray(1).getDouble(1);
      System.out.println(minLon);
      System.out.println(minLat);
//      Double minLon = -125.14030082759544;
//      Double maxLon = -113.95621879634841;
//      Double minLat = 32.52828384187941;
//      Double maxLat = 42.293559346676304;
      Double longRange =  maxLon - minLon;
      Double latRange =  maxLat - minLat;
      Double longUnit = longRange/1000;
      Double latUnit = latRange/1000;
      System.out.println(longUnit);
      System.out.println(latUnit);
      for(int i =0; i<queryResult.length(); i++){
        JSONObject pointJson = queryResult.getJSONObject(i);
        Double longitude = pointJson.getDouble("longitude");
        Double latitude = pointJson.getDouble("latitude");
        Double x = (longitude - minLon)/longUnit;
        Double y = (latitude - minLat)/latUnit;
        ig2.draw(new Ellipse2D.Double(x, y, 10, 10));
      }
      ImageIO.write(bi, "PNG", new File("C:\\tmp\\test.png"));
      long endTime   = System.currentTimeMillis();
      long totalTime = endTime - startTime;
      System.out.println("Time:" + totalTime);
    } catch (Exception e) {
      ExceptionUtil.throwIllegalArgumentException(e);
    } finally {
      System.out.println(queryResult.length());
//      System.out.println(queryResult.toString());
//      System.out.println("Time:" + totalTime);
    }
  }
  
  @Test
  public void createImageTest2() throws IOException{
//    JSONArray queryResult = new JSONArray();
    Integer width = 1000;
    Integer height = 1000;
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    Graphics2D ig2 = bi.createGraphics();
    ig2.draw(new Ellipse2D.Double(500, 500, 10, 10));
    //Draw some lines to the graphic
    ig2.drawLine(155,20,80,120);
    ig2.drawLine(300,1,700,32);

  //Export the result to a file
    ImageIO.write(bi, "PNG", new File("C:\\tmp\\test.png"));
  }

}
