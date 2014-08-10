package com.sportdataconnect.geometry.matrix;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author sportdataconnect
 */
public final class Matrix4x4 {
        
    private final double[] values;

    public Matrix4x4(final double[] m) {
        if (m.length != 16) {
            throw new IllegalArgumentException("A 4x4 matrix must have 16 values");
        }
        this.values = m;
    }
    
    public Matrix4x4 multiply(final double[] factor) {
        int i, j, e;
        double sum;
        final double[] result = new double[16];

//calcul of C=A*B
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
