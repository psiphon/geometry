package com.sportdataconnect.geometry.matrix;

import com.sportdataconnect.geometry.HomogenousPoint3D;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Representation of a 4 × 4 matrix.
 *
 * @author sportdataconnect
 */
public final class Matrix4x4 {

    public static final Matrix4x4 INDENTITY = Matrix4x4Builder.createMatrix()
            .withFirstRow(1.0, 0.0, 0.0, 0.0)
            .withSecondRow(0.0, 1.0, 0.0, 0.0)
            .withThirdRow(0.0, 0.0, 1.0, 0.0)
            .withFourthRow(0.0, 0.0, 0.0, 1.0)
            .build();

    private final double[] values;

    /**
     * Constructs a new 4 × 4 matrix from an array containing 9 elements. Values are ordered from left to right and top
     * to bottom with row values grouped together.
     * @param m
     */
    public Matrix4x4(final double[] m) {
        if (m.length != 16) {
            throw new IllegalArgumentException("A 4x4 matrix must have 16 values");
        }
        this.values = m;
    }

    /**
     * Returns the result of multiplying this matrix by the the one supplied. This will be on the left hand side of the
     * multiplication and rhs will be on the right.
     * @param factor
     * @return Returns the result of multiplying this matrix by the the one supplied
     */
    public Matrix4x4 multiply(final double[] factor) {
        int i, j, e;
        double sum;
        final double[] result = new double[16];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                sum = 0;
                for (e = 0; e < 4; e++) {
                    sum += values[i * 4 + e] * factor[e * 4 + j];
                }
                result[i * 4 + j] = sum;
            }
        }
        return new Matrix4x4(result);
    }

    /**
     * Returns the result of multiplying this matrix by the the one supplied. This will be on the left hand side of the
     * multiplication and rhs will be on the right.
     * @param factor
     * @return Returns the result of multiplying this matrix by the the one supplied
     */
    public Matrix4x4 multiply(final Matrix4x4 factor) {
        return multiply(factor.values);
    }

    /**
     * Returns whether or not this matrix is the same as the one supplied with a specified precision level
     * @param matrix
     * @param precision
     * @return Returns whether or not this matrix is the same as the one supplied with a specified precision level
     */
    public boolean equals(final Matrix4x4 matrix, final double precision) {
        for (int i = 0; i < 16; i++) {
            if (Math.abs(this.values[i] - matrix.values[i]) > precision) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the inverse of this matrix
     * @return Returns the inverse of this matrix
     */
    public Matrix4x4 invert() {
        double[] inv = new double[16];
        int i;

        inv[0] = values[5] * values[10] * values[15]
                - values[5] * values[11] * values[14]
                - values[9] * values[6] * values[15]
                + values[9] * values[7] * values[14]
                + values[13] * values[6] * values[11]
                - values[13] * values[7] * values[10];

        inv[4] = -values[4] * values[10] * values[15]
                + values[4] * values[11] * values[14]
                + values[8] * values[6] * values[15]
                - values[8] * values[7] * values[14]
                - values[12] * values[6] * values[11]
                + values[12] * values[7] * values[10];

        inv[8] = values[4] * values[9] * values[15]
                - values[4] * values[11] * values[13]
                - values[8] * values[5] * values[15]
                + values[8] * values[7] * values[13]
                + values[12] * values[5] * values[11]
                - values[12] * values[7] * values[9];

        inv[12] = -values[4] * values[9] * values[14]
                + values[4] * values[10] * values[13]
                + values[8] * values[5] * values[14]
                - values[8] * values[6] * values[13]
                - values[12] * values[5] * values[10]
                + values[12] * values[6] * values[9];

        inv[1] = -values[1] * values[10] * values[15]
                + values[1] * values[11] * values[14]
                + values[9] * values[2] * values[15]
                - values[9] * values[3] * values[14]
                - values[13] * values[2] * values[11]
                + values[13] * values[3] * values[10];

        inv[5] = values[0] * values[10] * values[15]
                - values[0] * values[11] * values[14]
                - values[8] * values[2] * values[15]
                + values[8] * values[3] * values[14]
                + values[12] * values[2] * values[11]
                - values[12] * values[3] * values[10];

        inv[9] = -values[0] * values[9] * values[15]
                + values[0] * values[11] * values[13]
                + values[8] * values[1] * values[15]
                - values[8] * values[3] * values[13]
                - values[12] * values[1] * values[11]
                + values[12] * values[3] * values[9];

        inv[13] = values[0] * values[9] * values[14]
                - values[0] * values[10] * values[13]
                - values[8] * values[1] * values[14]
                + values[8] * values[2] * values[13]
                + values[12] * values[1] * values[10]
                - values[12] * values[2] * values[9];

        inv[2] = values[1] * values[6] * values[15]
                - values[1] * values[7] * values[14]
                - values[5] * values[2] * values[15]
                + values[5] * values[3] * values[14]
                + values[13] * values[2] * values[7]
                - values[13] * values[3] * values[6];

        inv[6] = -values[0] * values[6] * values[15]
                + values[0] * values[7] * values[14]
                + values[4] * values[2] * values[15]
                - values[4] * values[3] * values[14]
                - values[12] * values[2] * values[7]
                + values[12] * values[3] * values[6];

        inv[10] = values[0] * values[5] * values[15]
                - values[0] * values[7] * values[13]
                - values[4] * values[1] * values[15]
                + values[4] * values[3] * values[13]
                + values[12] * values[1] * values[7]
                - values[12] * values[3] * values[5];

        inv[14] = -values[0] * values[5] * values[14]
                + values[0] * values[6] * values[13]
                + values[4] * values[1] * values[14]
                - values[4] * values[2] * values[13]
                - values[12] * values[1] * values[6]
                + values[12] * values[2] * values[5];

        inv[3] = -values[1] * values[6] * values[11]
                + values[1] * values[7] * values[10]
                + values[5] * values[2] * values[11]
                - values[5] * values[3] * values[10]
                - values[9] * values[2] * values[7]
                + values[9] * values[3] * values[6];

        inv[7] = values[0] * values[6] * values[11]
                - values[0] * values[7] * values[10]
                - values[4] * values[2] * values[11]
                + values[4] * values[3] * values[10]
                + values[8] * values[2] * values[7]
                - values[8] * values[3] * values[6];

        inv[11] = -values[0] * values[5] * values[11]
                + values[0] * values[7] * values[9]
                + values[4] * values[1] * values[11]
                - values[4] * values[3] * values[9]
                - values[8] * values[1] * values[7]
                + values[8] * values[3] * values[5];

        inv[15] = values[0] * values[5] * values[10]
                - values[0] * values[6] * values[9]
                - values[4] * values[1] * values[10]
                + values[4] * values[2] * values[9]
                + values[8] * values[1] * values[6]
                - values[8] * values[2] * values[5];

        double det = values[0] * inv[0] + values[1] * inv[4] + values[2] * inv[8] + values[3] * inv[12];

        if (det == 0) {
            return null;
        }

        det = 1.0 / det;

        double[] invOut = new double[16];
        for (i = 0; i < 16; i++) {
            invOut[i] = inv[i] * det;
        }

        return new Matrix4x4(invOut);
    }

    /**
     * Returns the point resulting from applying the transformation specified by this matrix to the supplied point
     * @param pt
     * @return Returns the point resulting from applying the transformation specified by this matrix to the supplied
     * point
     */
    public HomogenousPoint3D transform(final HomogenousPoint3D pt) {
        double nx = pt.getX() * values[0] + pt.getY() * values[4] + pt.getZ() * values[8] + pt.getW() * values[12];
        double ny = pt.getX() * values[1] + pt.getY() * values[5] + pt.getZ() * values[9] + pt.getW() * values[13];
        double nz = pt.getX() * values[2] + pt.getY() * values[6] + pt.getZ() * values[10] + pt.getW() * values[14];
        double nw = pt.getX() * values[3] + pt.getY() * values[7] + pt.getZ() * values[11] + pt.getW() * values[15];
        return new HomogenousPoint3D(nx, ny, nz, nw);
    }

    /**
     * Returns the matrix in a nicely formatted string. The result will have rows shown on separate lines and tabs
     * between columns
     * @return Returns the matrix in a nicely formatted string
     */
    public String print() {
        final StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        for (int i = 0; i < 4; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        for (int i = 4; i < 8; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        for (int i = 8; i < 12; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        for (int i = 12; i < 16; i++) {
            writer.print(values[i] + "\t");
        }
        writer.println();
        return stringWriter.toString();
    }
    
}
