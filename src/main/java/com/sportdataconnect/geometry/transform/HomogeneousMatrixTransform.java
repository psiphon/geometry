package com.sportdataconnect.geometry.transform;

import com.sportdataconnect.geometry.HomogenousPoint3D;
import com.sportdataconnect.geometry.matrix.Matrix4x4;

/**
 * A transformation based on a 4x4 matrix
 */
public final class HomogeneousMatrixTransform implements TransformHomogeneous3D {

    private Matrix4x4 matrix;

    /**
     * Instantiates a HomogeneousMatrixTransform that will used the supplied matrix
     * @param matrix
     */
    public HomogeneousMatrixTransform(final Matrix4x4 matrix) {
        this.matrix = matrix;
    }

    @Override
    public HomogenousPoint3D transform(final HomogenousPoint3D pt) {
        return matrix.transform(pt);
    }
}
