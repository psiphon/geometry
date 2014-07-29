package com.sportdataconnect.geometry.bezier;

import com.sportdataconnect.geometry.Point2D;

public final class Cubic {

    private final Quadratic q1;
    private final Quadratic q2;

    public Cubic(final Point2D p0, final Point2D p1, final Point2D p2, final Point2D p3) {
        q1 = new Quadratic(p0, p1, p2);
        q2 = new Quadratic(p1, p2, p3);
    }

    public Point2D pt(final double t) {
        return q1.pt(t).scale(1 - t).add(
                q2.pt(t).scale(t));
    }
}
