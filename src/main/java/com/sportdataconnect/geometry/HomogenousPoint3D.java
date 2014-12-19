package com.sportdataconnect.geometry;

/**
 * Representation of Homogenous 3D point where the additional coordinate is referred to as 'w'
 *
 * @author sportdataconnect
 */
public final class HomogenousPoint3D {
    
    private double x;
    private double y;
    private double z;
    private double w;

    /**
     * Constructs a new HomogenousPoint3D from the given 3D point with 'w' set to 1.0
     * @param pt
     */
    public HomogenousPoint3D(final Point3D pt) {
        this.x = pt.getX();
        this.y = pt.getY();
        this.z = pt.getZ();
        this.w = 1.0;
    }

    /**
     * Constructs an arbitrary HomogenousPoint3D from the given x, y, z and w values
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public HomogenousPoint3D(final double x, final double y, final double z, final double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public double getW() {
        return w;
    }

    /**
     * Scale all coordinates (including the 'w' coordinate) by the given scale factor
     * @param scaleFactor
     * @return
     */
    public HomogenousPoint3D scale(final double scaleFactor) {
        return new HomogenousPoint3D(
                this.x * scaleFactor, this.y * scaleFactor, this.z * scaleFactor, this.w * scaleFactor);
    }

    /**
     * Scale the point such that the 'w' coordinate becomes 1.0
     * @return
     */
    public HomogenousPoint3D normalize() {
        return this.scale(1.0 / w);
    }

    @Override
    public String toString() {
        return "Point4D{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }

    /**
     * Convert back to a normal 3D point by simply dropping the 'w' coordinate
     * @return
     */
    public Point3D toPoint3D() {
        return new Point3D(x, y, z);
    }
}
