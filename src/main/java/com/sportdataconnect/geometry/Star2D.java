package com.sportdataconnect.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a star polygon according to http://en.wikipedia.org/wiki/Star-shaped_polygon.
 * 
 * A star shaped polygon is useful as it can be broken down into triangles very simply.
 *
 * @author sportdataconnect
 */
public final class Star2D implements Polygon2D {
    
    private List<Point2D> points;
    private Point2D kernel;
    
    public Star2D(final List<Point2D> points) {
        this.points = new ArrayList<Point2D>(points);
        kernel = new Point2D(0.0, 0.0);
    }

    public Star2D(final List<Point2D> points, final Point2D kernel) {
        this.points = new ArrayList<Point2D>(points);
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
