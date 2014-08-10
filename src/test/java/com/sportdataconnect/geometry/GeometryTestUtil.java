package com.sportdataconnect.geometry;

import java.util.Collection;
import java.util.Set;

public class GeometryTestUtil {

    public static final double PRECISION = 0.000001;

    public static boolean pointsSimilar(Point2D p1, Point2D p2) {
        return Math.abs(p1.getX() - p2.getX()) < PRECISION && Math.abs(p1.getY() - p2.getY()) < PRECISION;
    }

    public static boolean collectionContainsSimilarPoints(Point2D pt, Collection<Point2D> points) {
        for (Point2D candidatePoint : points) {
            if (pointsSimilar(pt, candidatePoint)) {
                return true;
            }
        }
        return false;
    }
}
