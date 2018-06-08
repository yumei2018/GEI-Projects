package entity.core.map.data;

import java.awt.image.BufferedImage;

public interface Overlayable {
  //empty class that signifies overlayable layer
  public abstract BufferedImage getImage();
  
  public abstract float getTransparency();
  
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  public abstract int getType();
}
