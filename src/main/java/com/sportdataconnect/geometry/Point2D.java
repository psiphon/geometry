package com.sportdataconnect.geometry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author sportdataconnect
 */
public final class Point2D {
    
    private static final Logger LOG = LogManager.getLogger(Point2D.class);
    
    private double x;
    private double y;
    private double length;

    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
        length = Math.sqrt(
                this.x * this.x + this.y * this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getLength() {
        return length;
    }
    
    public Point2D add(final Point2D rhs) {
        return new Point2D(this.x + rhs.x, this.y + rhs.y);
    }
    
    public Point2D scale(final double scaleFactor) {
        return new Point2D(this.x * scaleFactor, this.y * scaleFactor);
    }
    
    public Point2D normalize() {
        return this.scale(1.0 / length);
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + "}";
    }

    public Point2D subtract(final Point2D pcCentre) {
        return new Point2D(this.x - pcCentre.x, this.y - pcCentre.y);
    }
    
    public double crossProduct(final Point2D rhs) {
        return this.x * rhs.y - this.y * rhs.x;
    }
    
    public double polarAngle() {
        if (y < 0) {
            return 2 * Math.PI + Math.atan2(y, x);
        } else {
            return Math.atan2(y, x);
        }
    }
    
    public static List<Point2D> createConvexPolygon(final Collection<Point2D> points) {
        LOG.debug(">>");
        Set<Point2D> remainingPoints = new HashSet<Point2D>(points);
        Point2D previousPoint = findBottomPoint(remainingPoints);
        Point2D firstPoint = previousPoint;
        LOG.debug("Adding point " + previousPoint);
        List<Point2D> resultPoints = new LinkedList<Point2D>();
        resultPoints.add(previousPoint);
        Point2D nextPoint = findNextPoint(remainingPoints, previousPoint, 0);
        while (nextPoint != null && nextPoint != firstPoint) {
            LOG.debug("Adding point " + nextPoint);
            resultPoints.add(nextPoint);
            remainingPoints.remove(nextPoint);
            double angle = nextPoint.subtract(previousPoint).polarAngle();
            previousPoint = nextPoint;
            LOG.debug("Current angle " + Math.toDegrees(angle));
            nextPoint = findNextPoint(remainingPoints, nextPoint, angle);
        }
        LOG.debug("<<");
        return resultPoints;
    }
    
    private static Point2D findBottomPoint(final Collection<Point2D> points) {
        if (points.size() < 0) {
            throw new IllegalArgumentException("Cannot find left most point from an empty set");
        }
        Iterator<Point2D> iter = points.iterator();
        Point2D lowest = iter.next();
        while (iter.hasNext()) {
            Point2D candidate = iter.next();
            if (lowest.getY() > candidate.getY()) {
                lowest = candidate;
            }
        }
        return lowest;
    }

    private static Point2D findNextPoint(final Collection<Point2D> points, final Point2D currentPoint,
            final double startingAngle) {
        //LOG.debug(">>findNextPoint");
        Iterator<Point2D> iter = points.iterator();
        Point2D closest = null;
        Double closestAngle = null;
        while (iter.hasNext()) {
            Point2D candidate = iter.next();
            //LOG.debug("candiate angle = " + Math.toDegrees(candidate.subtract(currentPoint).polarAngle()));
            //LOG.debug("starting angle angle = " + Math.toDegrees(startingAngle));
            double nextAngle = candidate.subtract(currentPoint).polarAngle() - startingAngle;
            if (nextAngle > 0.0) {
                if (closest == null) {
                    closest = candidate;
                    closestAngle = nextAngle;
                } else if (nextAngle < closestAngle) {
                    closest = candidate;
                    closestAngle = nextAngle;
                }
            }
        }
        if (closestAngle != null) {
            //LOG.debug("CLOSEST ANGLE FOUND = " + Math.toDegrees(closestAngle));
        }
        //LOG.debug("<<findNextPoint");
        return closest;
    }
}
