package com.sportdataconnect.geometry;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A polygon defined by a list of points. Triangles are calculated by creating a fan with the first point as the common
 * point.
 *
 * @author sportdataconnect
 */
public final class SimplePolygon2D implements Polygon2D {
    
    private ImmutableList<Point2D> points;

    public SimplePolygon2D(final List<Point2D> points) {
        if (points.size() < 3) {
            throw new InvalidGeometryException("Cannot create polygon with less than 3 vertices");
        }
        this.points = ImmutableList.copyOf(points);
    }

    public Point2D get(final int ix) {
        return points.get(ix);
    }

    public int numPoints() {
        return points.size();
    }

    public List<Triangle2D> toTriangles() {
        List<Triangle2D> result = new ArrayList<Triangle2D>();
        for (int i = 1; i < points.size() - 1; i++) {
            result.add(new Triangle2D(points.get(0), points.get(i), points.get(i + 1)));
        }
        return result;
    }

    public Bounds2D getBounds() {
        Iterator<Point2D> iter = points.iterator();
        Point2D pt = iter.next();
        double minX = pt.getX();
        double minY = pt.getY();
        double maxX = pt.getX();
        double maxY = pt.getY();
        while (iter.hasNext()) {
            pt = iter.next();
            minX = Math.min(minX, pt.getX());
            maxX = Math.max(maxX, pt.getX());
            minY = Math.min(minY, pt.getY());
            maxY = Math.max(maxY, pt.getY());
        }
        return new Bounds2D(minX, maxX, minY, maxY);
    }

    /**
     * Returns a polygon that has the same shape but is centred on the origin (the centre being the centre of it's
     * bounds)
     * @return
     */
    public SimplePolygon2D centreOnOrigin() {
        Point2D translation = getBounds().getCentre().scale(-1.0);
        List<Point2D> resultPoints = new ArrayList<Point2D>(points.size());
        for (Point2D pt : points) {
            resultPoints.add(pt.add(translation));
        }
        final SimplePolygon2D result = new SimplePolygon2D(resultPoints);
        return result;
    }
    
}
