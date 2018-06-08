package entity.core.controller.service;

import entity.core.context.MapImageContext;
import entity.core.map.data.*;
import entity.core.map.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
@RequestMapping("/mapservice")
public class MapService extends MultiActionController {

  @RequestMapping("/maptoimage")
  public void toImage(@RequestParam(value="features",required=false) JSONArray features
          , @RequestParam("layers") JSONObject layerJson
          , @RequestParam(value="encoded",required=false) Boolean encoded
          , HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      BufferedImage image = this.toImage(layerJson, features);
      if ((encoded == null) || (!encoded)) {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ImageIO.write(image, "PNG", response.getOutputStream());
      }
      else {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
          ImageIO.write(image, "PNG", baos);
          response.getWriter().write(Base64.encodeBase64String(baos.toByteArray()));
        }
      }
    } catch (Exception exp) {
      response.setContentType(MediaType.TEXT_HTML_VALUE);
      response.sendError(response.SC_INTERNAL_SERVER_ERROR, exp.getMessage());
    }
  }

  @RequestMapping("/generatemapimage")
  public void generateImage(@RequestParam(value="features",required=false) JSONArray features
          , @RequestParam("layers") JSONObject layerJson
          , @RequestParam(value="encoded",required=false) Boolean encoded
          , HttpServletRequest request, HttpServletResponse response) throws IOException {
    JSONObject jsonResponse = new JSONObject();
    try {
      BufferedImage image = null;
      if ((image = this.toImage(layerJson, features)) == null) {
        throw new Exception("The system could not generate the map image!");
      }
      try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        ImageIO.write(image, "PNG", baos);
        String mapId = Base64.encodeBase64String(request.getRequestedSessionId().getBytes());
        MapImageContext.getInstance().setImageBinary(mapId, baos.toByteArray());
        jsonResponse.put("data",mapId);
      }
      jsonResponse.put("success",true);
    } catch (Exception exp) {
      jsonResponse.put("success", false).put("error", exp.getMessage());
    } finally {
      response.getWriter().write(jsonResponse.toString());
    }
  }
  
  @RequestMapping("/mapimage/{mapid}")
  public void getMapImage(@PathVariable("mapid") String mapId
          ,HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      byte[] imgbinaries = null;
      if ((imgbinaries = MapImageContext.getInstance().getImageBinary(mapId)) == null) {
        throw new Exception("The map image for the modification request id is not valid!");
      }
      try (ByteArrayInputStream is = new ByteArrayInputStream(imgbinaries)) {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(is, response.getOutputStream());
      }
    }
    catch (Exception exp) {
      response.setContentType(MediaType.TEXT_HTML_VALUE);
      response.sendError(response.SC_INTERNAL_SERVER_ERROR, exp.getMessage());
    }
  }
  
  //<editor-fold defaultstate="collapsed" desc="Private Map to Image">
  /**
   * 
   * @param layerJson
   * @param features
   * @return BufferedImage image
   * @throws Exception 
   */
  private BufferedImage toImage(JSONObject layerJson,JSONArray features) throws Exception {
    BufferedImage image = null;
    if ((layerJson == null) || (layerJson.length() == 0)) {
      throw new Exception("The layers parameter is missing!");
    }
    String key="tiles";
    List<Overlayable> overlayLayers = new ArrayList<>();
    JSONArray layers = null;
    if (((layers = layerJson.optJSONArray(key)) != null) && (layers.length() > 0)) {
      overlayLayers.addAll(MosaicCollection.fromJson(layers));
    }
    key="dynamics";
    if (((layers = layerJson.optJSONArray(key)) != null) && (layers.length() > 0)) {
      overlayLayers.addAll(OverlayLayer.fromJson(layers));
    }
    BufferedImage graphics = null;
    if (((image = ImageUtil.overlayImages(overlayLayers)) == null)) {
      throw new Exception("The system cannot draw the image!");
    }
    if ((features != null) && (features.length() > 0)) {
      if ( ((graphics = ImageUtil.drawImage(features)) == null)
              || ((image = ImageUtil.mergeImage(image, graphics)) == null)) {
        throw new Exception("The system cannot draw the image!");
      }
    }
    return image;
  }
  //</editor-fold>
}
