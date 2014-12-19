package com.sportdataconnect.geometry.bezier;

import com.sportdataconnect.geometry.Point2D;

/**
 * Representation of a cubic Bézier curve in 2D space with a function for extracting the location at an arbitrary point
 * along the curve. A cubic Bézier curve is defined by 4 points - p0, p1, p2 and p3 and the curve starts at p0 heading
 * towards p1 and finishes at p3 heading from p2.
 *
 * @author sportdataconnect
 */
public final class Cubic2D {

    private final Quadratic2D q1;
    private final Quadratic2D q2;

    /**
     * Construct a Cubic2D with the specified points
     * @param p0
     * @param p1
     * @param p2
     * @param p3
     */
    public Cubic2D(final Point2D p0, final Point2D p1, final Point2D p2, final Point2D p3) {
        q1 = new Quadratic2D(p0, p1, p2);
        q2 = new Quadratic2D(p1, p2, p3);
    }

    /**
     * The function of this Bézier curve - with pt(0.0) = p0 and pt(1.0) = p3
     * @param t
     * @return
     */
    public Point2D pt(final double t) {
        return q1.pt(t).scale(1 - t).add(
                q2.pt(t).scale(t));
    }
}
