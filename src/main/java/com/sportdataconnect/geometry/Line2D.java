package com.sportdataconnect.geometry;

/**
 * Representation of an infinite line in 2D space. Line is representated by an arbitrary point on the line and a
 * direction vector.
 *
 * @author sportdataconnect
 */
public final class Line2D {

    private final Point2D point;
    private final Point2D direction;

    /**
     * Constructs a new line with the given point and direction
     * @param point
     * @param direction
     */
    public Line2D(final Point2D point, final Point2D direction) {
        this.point = point;
        if (direction.getLength() != 1.0) {
            this.direction = direction.normalize();
        } else {
            this.direction = direction;
        }
    }

    /**
     * Returns a new line that coincides with the given edge. The returned line point will be the same as the start
     * point of the edge.
     *
     * @param start
     * @param end
     * @return Returns a new line that coincides with the given edge
     */
    public static Line2D fromEdge(final Point2D start, final Point2D end) {
        return new Line2D(start, end.subtract(start));
    }

    /**
     * Returns an arbitrary point on the line (this point is preserved from the point that is specified at construction
     * time).
     * @return Returns an arbitrary point on the line
     */
    public Point2D getPoint() {
        return point;
    }

    /**
     * Returns the direction of the line. The direction is guaranteed to have a length of 1.
     * @return Returns the direction of the line.
     */
    public Point2D getDirection() {
        return direction;
    }

    /**
     * Returns this line translated by the specified vector
     * @param translation
     * @return Returns this line translated by the specified vector
     */
    public Line2D translate(final Point2D translation) {
        return new Line2D(point.add(translation), direction);
    }
}
