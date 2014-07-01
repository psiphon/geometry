package com.sportdataconnect.geometry;

import java.util.List;

/**
 *
 * @author sportdataconnect
 */
public interface Polygon2D {

    Point2D get(int ix);

    int numPoints();
    
    List<Triangle2D> toTriangles();

    Bounds2D getBounds();
    
}
