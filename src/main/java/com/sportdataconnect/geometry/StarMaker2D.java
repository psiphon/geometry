package com.sportdataconnect.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sportdataconnect
 */
public final class StarMaker2D {

    private StarMaker2D() {
    }
    
    public static Polygon2D createStar(final int numSegments, final double innerDistance, final double outerDistance) {
        List<Point2D> result = new ArrayList<Point2D>(numSegments * 2);
        final double segmentAngle = 2 * Math.PI / numSegments;
        for (int i = 0; i < numSegments; i++) {
            double angle1 = i * segmentAngle;
            double angle2 = (i + 0.5) * segmentAngle;
            result.add(new Point2D(Math.cos(angle1) * outerDistance, Math.sin(angle1) * outerDistance));
            result.add(new Point2D(Math.cos(angle2) * innerDistance, Math.sin(angle2) * innerDistance));
        }
        return new Star2D(result);
    }
    
}
