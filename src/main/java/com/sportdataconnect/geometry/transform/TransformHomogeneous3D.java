package com.sportdataconnect.geometry.transform;

import com.sportdataconnect.geometry.HomogenousPoint3D;

/**
 * Defines an arbitrary transformation (ie function) mapping from one HomogeneousPoint to another.
 */
public interface TransformHomogeneous3D {

    /**
     * Returns the result of the transformation applied the specified point.
     * @param pt
     * @return
     */
    HomogenousPoint3D transform(HomogenousPoint3D pt);
}
