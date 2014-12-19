package com.sportdataconnect.geometry;

/**
 * Representation of an edge in 3D space
 *
 * @author sportdataconnect
 */
public final class Edge3D {
    
    private Point3D startPoint;
    private Point3D endPoint;

    public Edge3D(final Point3D startPoint, final Point3D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * Returns the location of the end of the edge
     * @return
     */
    public Point3D getEndPoint() {
        return endPoint;
    }

    /**
     * Returns the location of the start of the edge
     * @return
     */
    public Point3D getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the direction from the start of the edge to the end
     * @return
     */
    public Point3D getDirection() {
        return endPoint.subtract(startPoint).normalize();
    }

    /**
     * Returns the offset from the start of the edge to the end of the edge
     * @return
     */
    public Point3D getVector() {
        return endPoint.subtract(startPoint);
    }

    @Override
    public String toString() {
        return "Edge3D{" + "startPoint=" + startPoint.toShortString() + ", endPoint=" + endPoint.toShortString() + '}';
    }
    
}
