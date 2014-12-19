package com.sportdataconnect.geometry;

import com.google.common.base.Optional;
import com.sportdataconnect.geometry.matrix.Matrix3x3;

/**
 * Representation of a 3D triangle
 *
 * @author sportdataconnect
 */
public final class Triangle3D {
    
    private Point3D pt1;
    private Point3D pt2;
    private Point3D pt3;

    public Triangle3D(final Point3D pt1, final Point3D pt2, final Point3D pt3) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.pt3 = pt3;
    }

    public Point3D getPt1() {
        return pt1;
    }

    public Point3D getPt2() {
        return pt2;
    }

    public Point3D getPt3() {
        return pt3;
    }

    /**
     * Returns the point that the line passing through the supplied edge intersects the plane containing this triangle
     * (ie as this is a plane line intersection the result may not be on either the original edge or the original triangle)
     * @param edge
     * @return
     */
    public Optional<Point3D> planeEdgeIntersection(final Edge3D edge) {
        double[] m = new double[9];
        m[0] = edge.getStartPoint().getX() - edge.getEndPoint().getX();
        m[1] = pt2.getX() - pt1.getX();
        m[2] = pt3.getX() - pt1.getX();
        
        m[3] = edge.getStartPoint().getY() - edge.getEndPoint().getY();
        m[4] = pt2.getY() - pt1.getY();
        m[5] = pt3.getY() - pt1.getY();
        
        m[6] = edge.getStartPoint().getZ() - edge.getEndPoint().getZ();
        m[7] = pt2.getZ() - pt1.getZ();
        m[8] = pt3.getZ() - pt1.getZ();
        Matrix3x3 matrix = new Matrix3x3(m);
        
        if (matrix.isInversible()) {
            Matrix3x3 inverseMatrix = matrix.getInverse();
            Point3D offset = edge.getStartPoint().subtract(this.pt1);
            Point3D intersectionVector = inverseMatrix.premult33(offset);
            Point3D intersection = edge.getStartPoint().add(edge.getVector().scale(intersectionVector.getX()));
            return Optional.of(intersection);
        } else {
            return Optional.absent();
        }
    }
}
