package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public abstract class Unprojector {
    
    public abstract Point3D unproject(final Point2D point2D);
    
    public final Triangle3D unproject(final Triangle2D triangle2D) {
        return new Triangle3D(
                unproject(triangle2D.getPt1()),
                unproject(triangle2D.getPt2()),
                unproject(triangle2D.getPt3()));
    }
    
}
