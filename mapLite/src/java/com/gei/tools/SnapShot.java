/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.esri.client.toolkit.overlays.InfoPopupOverlay;
import com.esri.core.geometry.Envelope;
import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.JMap;
import com.esri.map.LayerInitializeCompleteEvent;
import com.esri.map.LayerInitializeCompleteListener;
import com.esri.map.LayerList;
import com.esri.map.QueryMode;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/snapShot")
public class SnapShot  extends MultiActionController {
    ArcGISTiledMapServiceLayer baseLayer;
    static JMap map;
    @RequestMapping("/snap") 
    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
//        final ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer(
//        "http://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer");
//        JMap jMap = new JMap();
        Double xmin = Double.parseDouble(request.getParameter("xmin"));
        Double xmax = Double.parseDouble(request.getParameter("xmax"));
        Double ymin = Double.parseDouble(request.getParameter("ymin"));
        Double ymax = Double.parseDouble(request.getParameter("ymax"));
//        jMap.setExtent(new Envelope(xmin, ymin, xmax, ymax));
//        LayerList layers = jMap.getLayers();
//        layers.add(baseLayer);
//        BufferedImage img = jMap.exportMapImage();  
//        Graphics g = img.getGraphics();
//        ImageIO.write(img, "jpg" , response.getOutputStream());
        getMap(xmin, xmax, ymin, ymax);
//        SnapShot snapshotApp = new SnapShot();
//        map = createMap(xmin, xmax, ymin, ymax);
//        OutputStream os = response.getOutputStream();
//        os.write(map.);
          // create the UI, including the map, for the application.
//          JFrame frame = snapshotApp.createWindow();
//          frame.add(snapshotApp.createUI(xmin, xmax, ymin, ymax));
          
//          Robot robot = new Robot();
//          final Rectangle rect = frame.getBounds();
//          BufferedImage image = robot.createScreenCapture(rect);
//          final BufferedImage image = new BufferedImage(
//                frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
//             Graphics gr = image.getGraphics();
//             frame.printAll(gr);
//             gr.dispose();
//          boolean cont = true;
//          while(cont) {
//              if(map != null) {
//                  if(map.isReady()) {
//                    frame.setVisible(true);
//                    BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB); 
//                    Graphics2D graphics2D = image.createGraphics(); 
//                    frame.paint(graphics2D); 
//                    ImageIO.write(image, "png", new File("C:\\Users\\ileung\\Desktop\\test.png"));
//                    frame.setVisible(false);
//                    cont = false;
//                  }
//              }
//          }
//          
//          frame.dispose();
//          appWindow.get
//          appWindow.setVisible(true);
                
        
    }   
    
    private JFrame createWindow() {
    JFrame window = new JFrame("ArcGISFeatureLayer - Snapshot Mode");
    window.setBounds(100, 100, 1000, 700);    
    window.getContentPane().setLayout(new BorderLayout(0, 0));    
    return window;
  }
     public JComponent createUI(Double xmin, Double xmax, Double ymin, Double ymax) throws Exception {
    // application content
    JComponent contentPane = createContentPane();

    // description
//    JPanel description = createDescription();
//    contentPane.add(description);

    // map
    map = createMap(xmin, xmax, ymin, ymax);    
    contentPane.add(map);

    return contentPane;
  }
     private static JLayeredPane createContentPane() {
    JLayeredPane contentPane = new JLayeredPane();
    contentPane.setLayout(new BorderLayout(0, 0));
    contentPane.setVisible(true);
    return contentPane;
  }
private JMap createMap(Double xmin, Double xmax, Double ymin, Double ymax) throws Exception {

    final JMap jMap = new JMap();
    // -----------------------------------------------------------------------------------------
    // Base Layer
    // -----------------------------------------------------------------------------------------
    if(baseLayer == null) {
        baseLayer = new ArcGISTiledMapServiceLayer(
        "http://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");
    }
    // set to US extent
    jMap.setExtent(new Envelope(xmin, ymin, xmax, ymax));
    LayerList layers = jMap.getLayers();
    layers.add(baseLayer);    
    return jMap;
 
 
  
    }
    public void getMap(Double xmin, Double xmax, Double ymin, Double ymax) throws MalformedURLException, IOException{
        String url = "http://services.arcgisonline.com/arcgis/rest/services/World_Topo_Map/MapServer/export?bbox=" + xmin + "," + ymin + "," + xmax + "," + ymax
                +"&bboxSR=&layers=&layerDefs=&size=&imageSR=&format=png&transparent=false&dpi=&time=&layerTimeOptions=&dynamicLayers=&gdbVersion=&mapScale=&f=image";
        URLConnection connection = new URL(url).openConnection();
        InputStream stream = connection.getInputStream();
        Image img = ImageIO.read(stream);
        
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        
    }
}