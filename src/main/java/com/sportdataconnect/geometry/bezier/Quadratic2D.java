package com.sportdataconnect.geometry.bezier;

import com.sportdataconnect.geometry.Point2D;

/**
 * Representation of a quadratic Bézier curve in 2D space with a function for extracting the location at an arbitrary
 * point along the curve. A quadratic Bézier curve is defined by 3 points - p0, p1 and p2 and the curve starts at p0
 * heading towards p1 and finishes at p2 heading from p1.
 *
 * @author sportdataconnect
 */
public final class Quadratic2D {

    private final Point2D p0;
    private final Point2D p1;
    private final Point2D p2;

    public Quadratic2D(final Point2D p0, final Point2D p1, final Point2D p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point2D pt(final double t) {
        return p0.scale((1 - t) * (1 - t)).add(
                p1.scale(2 * (1 - t) * t)).add(
                p2.scale(t * t));
    }
}
