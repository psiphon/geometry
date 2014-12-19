package com.sportdataconnect.geometry.matrix;

import com.sportdataconnect.geometry.Point3D;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Representation of a 3 × 3 matrix.
 *
 * @author sportdataconnect
 */
public final class Matrix3x3 {
    
    private final double[] values;

    /**
     * Constructs a new 3 × 3 matrix from an array containing 9 elements. Values are ordered from left to right and top
     * to bottom with row values grouped together.
     * @param m
     */
    public Matrix3x3(final double[] m) {
        if (m.length != 9) {
            throw new IllegalArgumentException("A 3x3 matrix must have 9 values");
        }
        this.values = m;
    }

    /**
     * Returns the determinant of this matrix
     * @return Returns the determinant of this matrix
     */
    public double getDeterminant() {
        return   values[0] * (values[4] * values[8] - values[5] * values[7])
               - values[1] * (values[3] * values[8] - values[5] * values[6])
               + values[2] * (values[3] * values[7] - values[4] * values[6]);
    }

    /**
     * Returns whether or not this matrix can be inverted
     * @return Returns whether or not this matrix can be inverted
     */
    public boolean isInversible() {
        return getDeterminant() != 0;
    }

    /**
     * Returns the inverse of this matrix
     * @return Returns the inverse of this matrix
     * @throws java.lang.IllegalStateException If the matrix cannot be inverted
     */
    public Matrix3x3 getInverse() {
        final double[] out = new double[9];
        double det = getDeterminant();
        if (det != 0) {
            out[0] = (values[4] * values[8] - values[5] * values[7]) / det;
            out[1] = (values[3] * values[7] - values[1] * values[8]) / det;
            out[2] = (values[1] * values[5] - values[2] * values[4]) / det;
            
            out[3] = (values[5] * values[6] - values[3] * values[8]) / det;
            out[4] = (values[0] * values[8] - values[2] * values[6]) / det;
            out[5] = (values[2] * values[3] - values[0] * values[5]) / det;
            
            out[6] = (values[3] * values[7] - values[4] * values[6]) / det;
            out[7] = (values[1] * values[6] - values[0] * values[7]) / det;
            out[8] = (values[0] * values[4] - values[1] * values[3]) / det;
            return new Matrix3x3(out);
        } else {
            throw new IllegalStateException("Attempted to inverse a matrix that is not inversible");
        }
    }

    /**
     * Returns the result of multiplying this matrix by the the one supplied. This will be on the left hand side of the
     * multiplication and rhs will be on the right.
     * @param rhs
     * @return Returns the result of multiplying this matrix by the the one supplied
     */
    public Matrix3x3 multiply(final Matrix3x3 rhs) {
        int i, j, e;
        double sum;
        final double[] result = new double[9];
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                sum = 0;
                for (e = 0; e < 3; e++) {
                    sum += values[i * 3 + e] * rhs.values[e * 3 + j];
                }
                result[i * 3 + j] = sum;
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Returns the result of multiplying this matrix by the supplied Point3D as if it were a column vector. This can be
     * used to apply linear transformations such as scaling and rotation to the given point.
     *
     * @param pt
     * @return Returns the result of multiplying this matrix by the supplied Point3D
     */
    public Point3D premult33(final Point3D pt) {
        double x = values[0] * pt.getX() + values[1] * pt.getY() + values[2] * pt.getZ();
        double y = values[3] * pt.getX() + values[4] * pt.getY() + values[5] * pt.getZ();
        double z = values[6] * pt.getX() + values[7] * pt.getY() + values[8] * pt.getZ();
        return new Point3D(x, y, z);
    }

    /**
     * Returns the matrix in a nicely formatted string. The result will have rows shown on separate lines and tabs
     * between columns
     * @return Returns the matrix in a nicely formatted string
     */
    public String print() {
        final StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        for (int i = 0; i < 3; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        for (int i = 3; i < 6; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        for (int i = 6; i < 9; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        return stringWriter.toString();
    }
    
}
