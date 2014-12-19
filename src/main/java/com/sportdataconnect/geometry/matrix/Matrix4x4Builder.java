package com.sportdataconnect.geometry.matrix;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * A builder for creating 4x4 matrix instances
 */
public final class Matrix4x4Builder {

    public static Matrix4x4Builder createMatrix() {
        return new Matrix4x4Builder();
    }

    private double[] firstRow;
    private double[] secondRow;
    private double[] thirdRow;
    private double[] fourthRow;

    public Matrix4x4Builder withFirstRow(final double... firstRow) {
        checkArgument(firstRow.length == 4);
        this.firstRow = firstRow;
        return this;
    }

    public Matrix4x4Builder withSecondRow(final double... secondRow) {
        checkArgument(secondRow.length == 4);
        this.secondRow = secondRow;
        return this;
    }

    public Matrix4x4Builder withThirdRow(final double... thirdRow) {
        checkArgument(thirdRow.length == 4);
        this.thirdRow = thirdRow;
        return this;
    }

    public Matrix4x4Builder withFourthRow(final double... fourthRow) {
        checkArgument(fourthRow.length == 4);
        this.fourthRow = fourthRow;
        return this;
    }

    public Matrix4x4 build() {
        checkNotNull(firstRow);
        checkNotNull(secondRow);
        checkNotNull(thirdRow);
        checkNotNull(fourthRow);
        double[] values = new double[16];
        System.arraycopy(firstRow, 0, values, 0, 4);
        System.arraycopy(secondRow, 0, values, 4, 4);
        System.arraycopy(thirdRow, 0, values, 8, 4);
        System.arraycopy(fourthRow, 0, values, 12, 4);
        return new Matrix4x4(values);
    }
}
