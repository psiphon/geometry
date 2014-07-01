package com.sportdataconnect.geometry.matrix;

import com.sportdataconnect.geometry.Point3D;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author sportdataconnect
 */
public final class Matrix3x3 {
    
    private final double[] values;

    public Matrix3x3(final double[] m) {
        if (m.length != 9) {
            throw new IllegalArgumentException("A 3x3 matrix must have 9 values");
        }
        this.values = m;
    }
    
    public double getDeterminant() {
        return   values[0] * (values[4] * values[8] - values[5] * values[7])
               - values[1] * (values[3] * values[8] - values[5] * values[6])
               + values[2] * (values[3] * values[7] - values[4] * values[6]);
    }
    
    public boolean isInversible() {
        return getDeterminant() != 0;
    }
    
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
    
    public Matrix3x3 multiply(final Matrix3x3 factor) {
        int i, j, e;
        double sum;
        final double[] result = new double[9];
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                sum = 0;
                for (e = 0; e < 3; e++) {
                    sum += values[i * 3 + e] * factor.values[e * 3 + j];
                }
                result[i * 3 + j] = sum;
            }
        }
        return new Matrix3x3(result);
    }
    
    public Point3D premult33(final Point3D pt) {
        double x = values[0] * pt.getX() + values[1] * pt.getY() + values[2] * pt.getZ();
        double y = values[3] * pt.getX() + values[4] * pt.getY() + values[5] * pt.getZ();
        double z = values[6] * pt.getX() + values[7] * pt.getY() + values[8] * pt.getZ();
        return new Point3D(x, y, z);
    }

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
