package com.sportdataconnect.geometry;

import java.util.List;

/**
 * An interface to allow operations on polygons that aren't aware of the exact representation of polygon in use.
 * Provides access to the underlying points as an indexed list and the polygon broken down into a set of tesselated
 * triangles. By permitting multiple representations of polygons to be implemented, operations such as polygon
 * tesselation can be optimized.
 *
 * @author sportdataconnect
 */
public interface Polygon2D {

    /**
     * Returns point in the polygone with the specified (zero based) index
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
