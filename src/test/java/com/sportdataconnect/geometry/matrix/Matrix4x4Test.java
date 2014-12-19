package com.sportdataconnect.geometry.matrix;

import com.sportdataconnect.geometry.GeometryTestUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Matrix4x4Test {

    @Test
    public void given_identity_matrix_when_reverse_then_return_identity() {
        Matrix4x4 inverse = Matrix4x4.INDENTITY.invert();
        assertTrue(inverse.equals(Matrix4x4.INDENTITY, GeometryTestUtil.PRECISION));
    }

    @Test
    public void given_arbitrary_matrix_when_multiple_with_reverse_then_return_identity() {
        Matrix4x4 original = Matrix4x4Builder.createMatrix()
                .withFirstRow(1.0, 0.0, 3.0, 3.0)
                .withSecondRow(-3.0, 2.0, 2.0, 5.0)
                .withThirdRow(1.0, 1.0, 0.0, 3.0)
                .withFourthRow(3.0, 3.0, 2.5, 3.0)
                .build();
        Matrix4x4 inverse = original.invert();
        assertTrue(original.multiply(inverse).equals(Matrix4x4.INDENTITY, GeometryTestUtil.PRECISION));
        assertTrue(inverse.multiply(original).equals(Matrix4x4.INDENTITY, GeometryTestUtil.PRECISION));
    }
}
