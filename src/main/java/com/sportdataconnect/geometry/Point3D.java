package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public final class Point3D {
    
    public static final Point3D X_AXIS_DIR = new Point3D(1.0, 0.0, 0.0);
    public static final Point3D Y_AXIS_DIR = new Point3D(0.0, 1.0, 0.0);
    public static final Point3D Z_AXIS_DIR = new Point3D(0.0, 0.0, 1.0);
    
    private double x;
    private double y;
    private double z;
    private double length;

    public Point3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(
                this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public double getLength() {
        return length;
    }
    
    public Point3D add(final Point3D rhs) {
        return new Point3D(this.x + rhs.x, this.y + rhs.y, this.z + rhs.z);
    }

    public Point3D subtract(final Point3D rhs) {
        return new Point3D(this.x - rhs.x, this.y - rhs.y, this.z - rhs.z);
    }
    
    public Point3D scale(final double scaleFactor) {
        return new Point3D(this.x * scaleFactor, this.y * scaleFactor, this.z * scaleFactor);
    }
    
    public Point3D normalize() {
        return this.scale(1.0 / length);
    }
    
    public Point3D crossProduct(final Point3D rhs) {
        return new Point3D(
                this.y * rhs.z - this.z * rhs.y,
                this.z * rhs.x - this.x * rhs.z,
                this.x * rhs.y - this.y * rhs.x);
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    public String toShortString() {
        return "(" + x + ", " + y + ", " + z + ')';
    }
}
