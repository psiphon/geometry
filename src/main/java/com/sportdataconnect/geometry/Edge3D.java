package com.sportdataconnect.geometry;

/**
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

    public Point3D getEndPoint() {
        return endPoint;
    }

    public Point3D getStartPoint() {
        return startPoint;
    }

    public Point3D getDirection() {
        return endPoint.subtract(startPoint).normalize();
    }

    public Point3D getVector() {
        return endPoint.subtract(startPoint);
    }

    @Override
    public String toString() {
        return "Edge3D{" + "startPoint=" + startPoint.toShortString() + ", endPoint=" + endPoint.toShortString() + '}';
    }
    
}
