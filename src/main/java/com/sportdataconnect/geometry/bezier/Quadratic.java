package com.sportdataconnect.geometry.bezier;

import com.sportdataconnect.geometry.Point2D;

public final class Quadratic {

    private final Point2D p0;
    private final Point2D p1;
    private final Point2D p2;

    public Quadratic(final Point2D p0, final Point2D p1, final Point2D p2) {
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
