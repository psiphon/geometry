package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public final class HomogenousPoint3D {
    
    private double x;
    private double y;
    private double z;
    private double w;
    
    public HomogenousPoint3D(final Point3D pt) {
        this.x = pt.getX();
        this.y = pt.getY();
        this.z = pt.getZ();
        this.w = 1.0;
    }

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
    
//    public Point4D add(Point4D rhs) {
//        return new Point4D(this.x + rhs.x, this.y + rhs.y, this.z + rhs.z);
//    }
//
//    public Point4D subtract(Point4D rhs) {
//        return new Point4D(this.x - rhs.x, this.y - rhs.y, this.z - rhs.z);
//    }
    
    public HomogenousPoint3D scale(final double scaleFactor) {
        return new HomogenousPoint3D(
                this.x * scaleFactor, this.y * scaleFactor, this.z * scaleFactor, this.w * scaleFactor);
    }
    
    public HomogenousPoint3D normalize() {
        return this.scale(1.0 / w);
    }
    
//    public Point4D crossProduct(Point4D rhs) {
//        return new Point4D(
//                this.y * rhs.z - this.z * rhs.y,
//                this.z * rhs.x - this.x * rhs.z,
//                this.x * rhs.y - this.y * rhs.x);
//    }

    @Override
    public String toString() {
        return "Point4D{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
    
    public Point3D toPoint3D() {
        return new Point3D(x, y, z);
    }
}
