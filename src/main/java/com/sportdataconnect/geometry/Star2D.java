package com.sportdataconnect.geometry;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a star polygon according to http://en.wikipedia.org/wiki/Star-shaped_polygon.
 * 
 * A star shaped polygon is useful as concave polygons can be broken down to triangles without complex calculations.
 * The triangle calculation assumes the triangles formed by each neighbouring point pair and the kernal are non
 * intersecting.
 *
 * Star2D performs no checks that the supplies set of points actually form a star - if invalid points are supplies the
 * set of returned triangles will not be correct.
 *
 * @author sportdataconnect
 */
public final class Star2D implements Polygon2D {
    
    private ImmutableList<Point2D> points;
    private Point2D kernel;

    /**
     * Instantiates a new star with the given points, assuming a kernel at the origin.
     *
     * @param points
     */
    public Star2D(final List<Point2D> points) {
        this.points = ImmutableList.copyOf(points);
        kernel = new Point2D(0.0, 0.0);
    }

    /**
     * Instantiates a new star with the given points and kernel
     * @param points
     * @param kernel
     */
    public Star2D(final List<Point2D> points, final Point2D kernel) {
        this.points = ImmutableList.copyOf(points);
        this.kernel = kernel;
    }

    public Point2D get(final int ixStartFace) {
        return points.get(ixStartFace);
    }

    public int numPoints() {
        return points.size();
    }

    public List<Triangle2D> toTriangles() {
        List<Triangle2D> result = new ArrayList<Triangle2D>(points.size());
        for (int i = 0; i < points.size() - 1; i++) {
            result.add(new Triangle2D(points.get(i), points.get(i + 1), kernel));
        }
        result.add(new Triangle2D(points.get(points.size() - 1), points.get(0), kernel));
        return result;
    }

    public com.sportdataconnect.geometry.Bounds2D getBounds() {
        Iterator<Point2D> iter = points.iterator();
        Point2D pt = iter.next();
        double minX = pt.getX();
        double minY = pt.getY();
        double maxX = pt.getX();
        double maxY = pt.getY();
        while (iter.hasNext()) {
            pt = iter.next();
            minX = Math.min(minX, pt.getX());
            maxX = Math.min(maxX, pt.getX());
            minY = Math.min(minY, pt.getY());
            maxY = Math.min(maxY, pt.getY());
        }
        return new Bounds2D(minX, maxX, minY, maxY);
    }
    
}
