package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public final class Arc3D {
    
    private Point3D centre;
    private Point3D directionX;
    private Point3D directionY;
    private double startRadians;
    private double endRadians;

    public Arc3D(final Point3D centre, final Point3D directionX, final Point3D directionY, final double startRadians,
            final double endRadians) {
        this.centre = centre;
        this.directionX = directionX;
        this.directionY = directionY;
        this.startRadians = startRadians;
        this.endRadians = endRadians;
    }

    public Point3D getCentre() {
        return centre;
    }

    public Point3D getDirectionX() {
        return directionX;
    }

    public Point3D getDirectionY() {
        return directionY;
    }

    public double getEndRadians() {
        return endRadians;
    }

    public double getStartRadians() {
        return startRadians;
    }
    
}
