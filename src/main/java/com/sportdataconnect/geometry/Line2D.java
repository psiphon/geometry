package com.sportdataconnect.geometry;

public final class Line2D {

    private final Point2D point;
    private final Point2D direction;

    public Line2D(final Point2D point, final Point2D direction) {
        this.point = point;
        if (direction.getLength() != 1.0) {
            this.direction = direction.normalize();
        } else {
            this.direction = direction;
        }
    }

    public static Line2D fromEdge(final Point2D start, final Point2D end) {
        return new Line2D(start, end.subtract(start));
    }

    public Point2D getPoint() {
        return point;
    }

    public Point2D getDirection() {
        return direction;
    }

    public Line2D translate(final Point2D translation) {
        return new Line2D(point.add(translation), direction);
    }
}
