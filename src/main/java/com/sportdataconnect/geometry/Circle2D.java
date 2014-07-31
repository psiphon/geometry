package com.sportdataconnect.geometry;

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
     * Returns the nearest point that the specified line intersects the circle. If the line does not intersect the circle
     * then null is returned. The 'nearest' point is the point nearest to the line's point (i.e. two geometrically equivalent lines
     * may return different results depending on what point they are defined with).
     * @param line
     * @return Returns the nearest point that the specified line intersects the circle
     */
    public Point2D getNearestLineIntersection(final Line2D line) {
        /* Line circle intersection calculation assumes that the circle is centred at the origin so translate the line
           first */
        Line2D normalisedLine = line.translate(centre.scale(-1));
        Point2D endPoint = normalisedLine.getPoint().add(normalisedLine.getDirection());
        double bigD = normalisedLine.getPoint().getX() * endPoint.getY()
                      - endPoint.getX() * normalisedLine.getPoint().getY();
        final double dr = normalisedLine.getDirection().getLength();
        final double dy = normalisedLine.getDirection().getY();
        final double dx = normalisedLine.getDirection().getX();
        double discriminant = radius * radius * dr * dr - bigD * bigD;
        if (discriminant > 0) {
            /* line intersects in two places */
            double x1 = (bigD * dy + sgn(dy) * dx * Math.sqrt(radius * radius * dr * dr - bigD * bigD)) / (dr * dr);
            double x2 = (bigD * dy - sgn(dy) * dx * Math.sqrt(radius * radius * dr * dr - bigD * bigD)) / (dr * dr);
            double y1 = (-bigD * dx + Math.abs(dy) * Math.sqrt(radius * radius * dr * dr - bigD * bigD)) / (dr * dr);
            double y2 = (-bigD * dx - Math.abs(dy) * Math.sqrt(radius * radius * dr * dr - bigD * bigD)) / (dr * dr);
            Point2D p1 = new Point2D(x1, y1);
            Point2D p2 = new Point2D(x2, y2);
            if (p1.distanceFrom(normalisedLine.getPoint()) > p2.distanceFrom(normalisedLine.getPoint())) {
                return p2.add(centre);
            } else {
                return p1.add(centre);
            }
        } else if (discriminant == 0) {
            /* line intersects at a single point (i.e. is a tangent) */
            double x = (bigD * dy) / (dr * dr);
            double y = (-bigD * dx) / (dr * dr);
            return new Point2D(x, y).add(centre);
        } else {
            /* line does not intersect the circle */
            return null;
        }
    }

    private double sgn(final double x) {
        if (x < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
