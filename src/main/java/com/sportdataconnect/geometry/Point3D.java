package com.sportdataconnect.geometry;

/**
 * Representation of a point in 3D space.
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

    /**
     * Returns the distance of the point from the origin. The vector length.
     * @return Returns the distance of the point from the origin
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns the sum of this point and the supplied rhs parameter. The vector sum.
     * @param rhs
     * @return Returns the sum of this point and the supplied rhs parameter.
     */
    public Point3D add(final Point3D rhs) {
        return new Point3D(this.x + rhs.x, this.y + rhs.y, this.z + rhs.z);
    }

    /**
     * Returns the result of subtracting the supplied point from this one. Vector
     * subtraction
     * @param rhs
     * @return Returns the result of subtracting the supplied point from this one
     */
    public Point3D subtract(final Point3D rhs) {
        return new Point3D(this.x - rhs.x, this.y - rhs.y, this.z - rhs.z);
    }

    /**
     * Returns this point after being scaled by the supplied value. Scalar multiplication.
     * @param scaleFactor
     * @return Returns this point after being scaled by the supplied value
     */
    public Point3D scale(final double scaleFactor) {
        return new Point3D(this.x * scaleFactor, this.y * scaleFactor, this.z * scaleFactor);
    }

    /**
     * Returns this point after being scaled such that it's length is one. The
     * unit vector with the same direction)
     * @return Returns this point after being scaled such that it's length is one.
     */
    public Point3D normalize() {
        return this.scale(1.0 / length);
    }

    /**
     * Returns this cross product (or vector product) of this point and the supplied point.
     * @param rhs
     * @return Returns this cross product (or vector product) of this point and the supplied point
     */
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

    /**
     * Returns the compact string representation of the point - i.e. (X, Y, Z)
     * @return
     */
    public String toShortString() {
        return "(" + x + ", " + y + ", " + z + ')';
    }
}
