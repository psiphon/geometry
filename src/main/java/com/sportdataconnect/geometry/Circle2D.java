package com.sportdataconnect.geometry;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a circle in 2D space. The circle is defined by a centre point and a radius
 *
 * @author sportdataconnect
 */
public final class Circle2D {

    private final Point2D centre;
    private final double radius;

    public Circle2D(final Point2D centre, final double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    /**
     * Returns the centre of the circle
     * @return Returns the centre of the circle
     */
    public Point2D getCentre() {
        return centre;
    }

    /**
     * Returns the radius of the circle
     * @return Returns the radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the nearest point that the specified line intersects the circle. If the line does not intersect the
     * circle then null is returned. The 'nearest' point is the point nearest to the line's point (i.e. two
     * geometrically equivalent lines may return different results depending on what point they are defined with).
     * @param line
     * @return Returns the nearest point that the specified line intersects the circle
     */
    public Point2D getNearestLineIntersection(final Line2D line) {
        /* Line circle intersection calculation assumes that the circle is centred at the origin so translate the line
           first */
        Line2D normalisedLine = line.translate(centre.scale(-1));
        Point2D endPoint = normalisedLine.getPoint().add(normalisedLine.getDirection());
        double bigD = normalisedLine.getPoint().crossProduct(endPoint);
        double discriminant = radius * radius - bigD * bigD;
        if (discriminant > 0) {
            Point2D[] points = getBothLineIntersectionPoints(normalisedLine, bigD);
            return normalisedLine.getPoint().findNearest(points).add(centre);
        } else if (discriminant == 0) {
            return getSingleLineIntersectionPoint(normalisedLine, bigD);
        } else {
            /* line does not intersect the circle */
            return null;
        }
    }

    public Set<Point2D> getLineIntersectionPoints(final Line2D line) {
        /* Line circle intersection calculation assumes that the circle is centred at the origin so translate the line
           first */
        Line2D normalisedLine = line.translate(centre.scale(-1));
        Point2D endPoint = normalisedLine.getPoint().add(normalisedLine.getDirection());
        double bigD = normalisedLine.getPoint().crossProduct(endPoint);
        double discriminant = radius * radius - bigD * bigD;
        if (discriminant > 0) {
            Point2D[] points = getBothLineIntersectionPoints(normalisedLine, bigD);
            Set<Point2D> result = new HashSet<Point2D>();
            result.add(points[0].add(centre));
            result.add(points[1].add(centre));
            return result;
        } else if (discriminant == 0) {
            Set result = new HashSet();
            result.add(getSingleLineIntersectionPoint(normalisedLine, bigD));
            return result;
        } else {
            /* line does not intersect the circle */
            return new HashSet<Point2D>();
        }
    }

    private Point2D getSingleLineIntersectionPoint(final Line2D normalisedLine, final double bigD) {
        final double dy = normalisedLine.getDirection().getY();
        final double dx = normalisedLine.getDirection().getX();
        double x = (bigD * dy);
        double y = (-bigD * dx);
        return new Point2D(x, y).add(centre);
    }

    private Point2D[] getBothLineIntersectionPoints(final Line2D normalisedLine, final double bigD) {
        final double dy = normalisedLine.getDirection().getY();
        final double dx = normalisedLine.getDirection().getX();
        double x1 = (bigD * dy + sgn(dy) * dx * Math.sqrt(radius * radius - bigD * bigD));
        double x2 = (bigD * dy - sgn(dy) * dx * Math.sqrt(radius * radius - bigD * bigD));
        double y1 = (-bigD * dx + Math.abs(dy) * Math.sqrt(radius * radius - bigD * bigD));
        double y2 = (-bigD * dx - Math.abs(dy) * Math.sqrt(radius * radius - bigD * bigD));
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(x1, y1);
        points[1] = new Point2D(x2, y2);
        return points;
    }

    private double sgn(final double x) {
        if (x < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
