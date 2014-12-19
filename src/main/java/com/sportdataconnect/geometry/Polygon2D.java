package com.sportdataconnect.geometry;

import java.util.List;

/**
 * This interface defines what operations must be provided by a polygon. Underlying points may be accessed as an indexed
 * list and the polygon may be broken down into a set of tesselated triangles. Permitting different representations of
 * polygons allows tessellation to be optimized.
 *
 * @author sportdataconnect
 */
public interface Polygon2D {

    /**
     * Returns point in the polygon with the specified (zero based) index
     * @param ix
     * @return
     */
    Point2D get(int ix);

    /**
     * Returns the total number of points in the polygon
     * @return Returns the total number of points in the polygon
     */
    int numPoints();

    /**
     * Returns the polygons broken down into tesselated triangles. Combined together the triangles make up the entire
     * area of the polygon but do not themselves
     * @return Returns the polygons broken down into tesselated triangles
     */
    List<Triangle2D> toTriangles();

    /**
     * Returns the bounding rectangle of the entire polygon.
     * @return Returns the bounding rectangle of the entire polygon
     */
    Bounds2D getBounds();
    
}
