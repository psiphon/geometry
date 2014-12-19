package com.sportdataconnect.geometry.transform;

import com.sportdataconnect.geometry.Point2D;
import com.sportdataconnect.geometry.Point3D;

/**
 * Defines an arbitrary transformation (ie function) mapping from a 3D point to a 2D point.
 *
 * @author sportdataconnect
 */
public interface Transform3D2D {

    /**
     * Returns the result of the transformation applied the specified point.
     * @param pt
     * @return
     */
    Point2D transform(Point3D pt);
    
}
