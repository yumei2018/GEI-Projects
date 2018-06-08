package entity.core.map.data;

import entity.core.map.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class MosaicCollection implements Overlayable,Collection<MosaicLayer> {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  public static final Logger logger = Logger.getLogger(MosaicCollection.class.getName());
//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  protected List<MosaicLayer> layers;
  protected ClipOptions clipOptions;
  protected float transparency;
  protected int minRow;
  protected int maxRow;
  protected int minCol;
  protected int maxCol;
  protected BufferedImage mosaic;

  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.layers = null;
    this.clipOptions = null;
    this.mosaic = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public MosaicCollection(List<MosaicLayer> layers, float transparency, ClipOptions clipOptions) {
    this.layers = layers;
    this.transparency = transparency;
    this.clipOptions = clipOptions;
    //a little housekeeping
    minRow = 999999;
    minCol = 999999;
    maxRow = -1;
    maxCol = -1;
    if (layers != null) {
      for (MosaicLayer layer : layers) {
        if (minRow >= layer.getRow()) {
          minRow = layer.getRow();
        }
        if (minCol >= layer.getCol()) {
          minCol = layer.getCol();
        }
        if (maxRow <= layer.getRow()) {
          maxRow = layer.getRow();
        }
        if (maxCol <= layer.getCol()) {
          maxCol = layer.getCol();
        }
      }
    }
    //mosaic images
    mosaic = null;
    try {
      ImageUtil util = new ImageUtil();
      mosaic = util.mosaicImages(this);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error Mosaicing Images, " + e.getMessage());
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"MosaicCollection"
          ,e.getMessage()
        )
      );
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  public List<MosaicLayer> getLayers() {
    return layers;
  }
  
  public void setLayers(List<MosaicLayer> layers) {
    this.layers = layers;
  }
  
  public int getMinRow() {
    return minRow;
  }
  
  public int getMinCol() {
    return minCol;
  }
  
  public int getMaxRow() {
    return maxRow;
  }
  
  public int getMaxCol() {
    return maxCol;
  }
  
  public MosaicLayer getLayer(int row, int col) {
    MosaicLayer layer = null;
    if (layers != null) {
      for (MosaicLayer l : layers) {
        if (l.getRow() == row && l.getCol() == col) {
          layer = l;
          break;
        }
      }
    }
    return layer;
  }
  
  public ClipOptions getClipOptions() {
    return clipOptions;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Overlayable Implementation Methods">
  @Override
  public float getTransparency() {
    return transparency;
  }
  
  @Override
  public BufferedImage getImage() {
    return mosaic;
  }
  
  @Override
  public int getHeight() {
    return mosaic != null ? mosaic.getHeight() : 0;
  }
  
  @Override
  public int getWidth() {
    return mosaic != null ? mosaic.getWidth() : 0;
  }
  
  @Override
  public int getType() {
    return mosaic.getType();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  public static MosaicCollection fromJson(JSONObject json) throws Exception {
    MosaicCollection collection = null;
    List<MosaicLayer> layers = new ArrayList<>();
    try {
      JSONArray jlayers = json.getJSONArray("tiles");
      for (int i = 0; i < jlayers.length(); i++) {
        JSONObject jlayer = jlayers.getJSONObject(i);
        MosaicLayer layer = MosaicLayer.fromJson(jlayer);
        layers.add(layer);
      }
      //color
      float transparency = (float) json.getDouble("transparency");
      //clip options - optional
      ClipOptions options = ClipOptions.fromJson(json.optJSONObject("clipOptions"));
      collection = new MosaicCollection(layers, transparency, options);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Invalid input json, " + e.getMessage());
      throw e;
    }
    return collection;
  }
  
  public static List<MosaicCollection> fromJson(JSONArray json) throws Exception {
    List<MosaicCollection> collections = new ArrayList<>();
    try {
      for (int i = 0; i < json.length(); i++) {
        MosaicCollection collection = fromJson(json.getJSONObject(i));
        collections.add(collection);
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Invalid input json, " + e.getMessage());
      throw e;
    }
    return collections;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Collection Implementation Methods">
  @Override
  public int size() {
    return this.layers.size();
  }
  
  @Override
  public boolean isEmpty() {
    return this.layers.isEmpty();
  }
  
  @Override
  public boolean contains(Object o) {
    return this.layers.contains(o);
  }
  
  @Override
  public Iterator<MosaicLayer> iterator() {
    return this.layers.iterator();
  }
  
  @Override
  public Object[] toArray() {
    return this.layers.toArray();
  }
  
  @Override
  public <T> T[] toArray(T[] a) {
    return this.layers.toArray(a);
  }
  
  @Override
  public boolean add(MosaicLayer e) {
    return this.layers.add(e);
  }
  
  @Override
  public boolean remove(Object o) {
    return this.layers.remove(o);
  }
  
  @Override
  public boolean containsAll(Collection<?> c) {
    return this.layers.containsAll(c);
  }
  
  @Override
  public boolean addAll(Collection<? extends MosaicLayer> c) {
    return this.layers.addAll(c);
  }
  
  @Override
  public boolean removeAll(Collection<?> c) {
    return this.layers.removeAll(c);
  }
  
  @Override
  public boolean retainAll(Collection<?> c) {
    return this.layers.retainAll(c);
  }
  
  @Override
  public void clear() {
    this.layers.clear();
  }
  
  public MosaicLayer get(int idx) {
    return this.layers.get(idx);
  }
  //</editor-fold>
}