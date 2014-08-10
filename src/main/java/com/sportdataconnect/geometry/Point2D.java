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
 * Representation of a point in 2D space.
 *
 * @author sportdataconnect
 */
public final class Point2D {

    public static final Point2D X_AXIS_DIR = new Point2D(1.0, 0.0);
    public static final Point2D Y_AXIS_DIR = new Point2D(0.0, 1.0);
    
    private static final Logger LOG = LogManager.getLogger(Point2D.class);
    
    private final double x;
    private final double y;
    private final double length;

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
    
    /**
     * Returns the distance of the point from the origin. The vector length.
     * @return Returns the distance of the point from the origin
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Returns the sum of this point and the supplied rhs parameter. The vector sum.
     * @param rhs
     * @return Returns the sum of this point and the supplied rhs parameter.
     */
    public Point2D add(final Point2D rhs) {
        return new Point2D(this.x + rhs.x, this.y + rhs.y);
    }
    
    /**
     * Returns this point after being scaled by the supplied value. Scalar multiplication.
     * @param scaleFactor
     * @return Returns this point after being scaled by the supplied value
     */
    public Point2D scale(final double scaleFactor) {
        return new Point2D(this.x * scaleFactor, this.y * scaleFactor);
    }
    
    /**
     * Returns this point after being scaled such that it's length is one. The
     * unit vector with the same direction)
     * @return Returns this point after being scaled such that it's length is one.
     */
    public Point2D normalize() {
        return this.scale(1.0 / length);
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + "}";
    }

    /**
     * Returns the result of subtracting the supplied point from this one. Vector
     * subtraction
     * @param rhs
     * @return Returns the result of subtracting the supplied point from this one
     */
    public Point2D subtract(final Point2D rhs) {
        return new Point2D(this.x - rhs.x, this.y - rhs.y);
    }
    
    /**
     * Returns the cross product of this vector and the supplied vector
     * @param rhs
     * @return Returns the cross product of this vector and the supplied vector
     */
    public double crossProduct(final Point2D rhs) {
        return this.x * rhs.y - this.y * rhs.x;
    }

    /**
     * Returns the direction that is perpendicular to this point
     * @return Returns the direction that is perpendicular to this point
     */
    public Point2D crossDir() {
        Point3D pt3D = new Point3D(x, y, 0.0);
        Point3D crossDir3D = pt3D.crossProduct(Point3D.Z_AXIS_DIR).normalize();
        return new Point2D(crossDir3D.getX(), crossDir3D.getY());
    }
    
    /**
     * Returns the angle anti-clockwise angle between this vector and the X-axis
     * @return Returns the angle anti-clockwise angle between this vector and the X-axis
     */
    public double polarAngle() {
        if (y < 0) {
            return 2 * Math.PI + Math.atan2(y, x);
        } else {
            return Math.atan2(y, x);
        }
    }

    /**
     * Returns the distance of this point from the specified point
     * @param point
     * @return Returns the distance of this point from the specified point
     */
    public double distanceFrom(final Point2D point) {
        double dx = point.x - this.x;
        double dy = point.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the average location of the points in the collection
     * @param pts
     * @return Returns the average location of the points in the collection
     */
    public static Point2D findAverage(final Collection<Point2D> pts) {
        double sumX = 0;
        double sumY = 0;
        for (Point2D pt : pts) {
            sumX += pt.x;
            sumY += pt.y;
        }
        return new Point2D(sumX / pts.size(), sumY / pts.size());
    }

    /**
     *
     * Returns the smallest possible convex polygon that contains all of the supplied points.
     * The points are returned anti-clockwise.
     * @param points
     * @return Returns the smallest possible convex polygon that contains all of the supplied points
     * @param points
     * @return
     * @throws InvalidGeometryException If the supplied collection of points is empty
     */
    public static List<Point2D> createConvexPolygon(final Collection<Point2D> points) throws InvalidGeometryException {
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
            throw new InvalidGeometryException("Cannot find left most point from an empty set");
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
            if (nextAngle >= 0.0 && candidate != currentPoint) {
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

    /**
     * Returns the point in the supplied collection that is nearest to this point
     * @param points
     * @return Returns the point in the supplied collection that is nearest to this point
     * @throws InvalidGeometryException If the supplied set of points is empty
     */
    public Point2D findNearest(final Point2D[] points) throws InvalidGeometryException {
        Point2D result = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Point2D candidate : points) {
            double candidateDistance = distanceFrom(candidate);
            if (candidateDistance < nearestDistance) {
                result = candidate;
                nearestDistance = candidateDistance;
            }
        }
        if (result != null) {
            return result;
        } else {
            throw new InvalidGeometryException("Cannot find the nearest point in an empty list");
        }
    }
}
