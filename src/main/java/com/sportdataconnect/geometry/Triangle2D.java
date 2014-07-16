package com.sportdataconnect.geometry;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author sportdataconnect
 */
public final class Triangle2D implements Polygon2D {
    
    private static final Logger LOG = LogManager.getLogger(Triangle2D.class);
    
    private Point2D pt1;
    private Point2D pt2;
    private Point2D pt3;
    
    private ImmutableList<Triangle2D> thisAsList = null;

    public Triangle2D(final Point2D pt1, final Point2D pt2, final Point2D pt3) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.pt3 = pt3;
    }

    public Point2D getPt1() {
        return pt1;
    }

    public Point2D getPt2() {
        return pt2;
    }

    public Point2D getPt3() {
        return pt3;
    }

    public Point2D get(final int ix) {
        switch (ix) {
            case 0: return pt1;
            case 1: return pt2;
            case 2: return pt3;
            default: throw new IndexOutOfBoundsException("Expected point index of 0..2 but encountered " + ix);
        }
    }

    public int numPoints() {
        return 3;
    }

    public List<Triangle2D> toTriangles() {
        if (thisAsList == null) {
            thisAsList = ImmutableList.of(this);
        }
        return thisAsList;
    }
    
    public boolean contains(final Point2D pt) {
        Point2D ab = pt2.subtract(pt1);
        Point2D bc = pt3.subtract(pt2);
        Point2D ca = pt1.subtract(pt3);
        Point2D ap = pt.subtract(pt1);
        Point2D bp = pt.subtract(pt2);
        Point2D cp = pt.subtract(pt3);
        
        double cpa = ab.crossProduct(ap);
        double cpb = bc.crossProduct(bp);
        double cpc = ca.crossProduct(cp);
        if (cpa < 0 && cpb < 0 && cpc < 0) {
            return true;
        } else {
            return (cpa > 0 && cpb > 0 && cpc > 0);
        }
    }

    public Bounds2D getBounds() {        
        double minX = Math.min(pt1.getX(), Math.min(pt2.getX(), pt3.getX()));
        double minY = Math.min(pt1.getY(), Math.min(pt2.getY(), pt3.getY()));
        double maxX = Math.max(pt1.getX(), Math.max(pt2.getX(), pt3.getX()));
        double maxY = Math.max(pt1.getY(), Math.max(pt2.getY(), pt3.getY()));
        return new Bounds2D(minX, maxX, minY, maxY);
    }
    
}
