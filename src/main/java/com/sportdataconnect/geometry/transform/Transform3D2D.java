package com.sportdataconnect.geometry.transform;

import com.sportdataconnect.geometry.Point2D;
import com.sportdataconnect.geometry.Point3D;

/**
 *
 * @author sportdataconnect
 */
public interface Transform3D2D {
    
    Point2D transform(Point3D pt);
    
}
