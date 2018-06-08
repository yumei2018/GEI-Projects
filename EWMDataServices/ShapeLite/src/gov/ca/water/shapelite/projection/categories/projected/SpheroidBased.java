package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * Spheroid Based Coordinate Systems.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SpheroidBased extends CoordinateSystemCategory {

  /**
   * Lambert 2 (Central France).
   */
  private final ProjectionInfo lambert2;

  /**
   * Lambert 2.
   */
  private final ProjectionInfo lambert2Wide;

  /**
   * Creates a new instance of SpheroidBased.
   */
  public SpheroidBased() {
    lambert2 = ProjectionInfo.fromProj4String("+proj=lcc "
        + "+lat_1=45.89893890000052 +lat_2=47.69601440000037 +lat_0=46.8 "
        + "+lon_0=2.33722917 +x_0=600000 +y_0=200000 +ellps=clrk80 "
        + "+units=m +no_defs").orElse(null);
    lambert2Wide = ProjectionInfo.fromProj4String("+proj = lcc + lat_1 = "
        + "45.89891889999931 + lat_2 = 47.69601440000037 + lat_0 = 46.8 + "
        + "lon_0 = 2.33722917 + x_0 = 600000 + y_0 = 2200000 + a = 6378249.145"
        + " + b = 6356514.96582849 + units = m + no_defs").orElse(null);
  }

  /**
   * @return the lambert2
   */
  public final ProjectionInfo getLambert2() {
    return lambert2.copy();
  }

  /**
   * @return the lambert2Wide
   */
  public final ProjectionInfo getLambert2Wide() {
    return lambert2Wide.copy();
  }

}
