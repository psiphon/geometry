package com.sportdataconnect.geometry.transform;

import com.sportdataconnect.geometry.Point2D;

/**
 * Defines an arbitrary transformation (ie function) mapping from one 2D point to another.
 *
 * @author sportdataconnect
 */
public interface Transform2D2D {

    /**
     * Returns the result of the transformation applied the specified point.
     * @param pt
     * @return Returns the result of the transformation applied the specified point.
     */
    Point2D transform(Point2D pt);
    
}
