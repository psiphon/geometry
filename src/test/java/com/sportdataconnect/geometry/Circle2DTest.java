package com.sportdataconnect.geometry;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class Circle2DTest {

    @Test
    public void testLineDoesntInsersectCircle() {
        Circle2D circle = new Circle2D(new Point2D(1.0, 1.1), 1.0);
        Line2D line = new Line2D(new Point2D(-1.0, 0.0), new Point2D(0.5, 1.0));

        assertNull(circle.getNearestLineIntersection(line));
    }

    @Test
    public void testParallelTangentCases() {
        Circle2D circle = new Circle2D(new Point2D(0.0, 0.0), 1.0);

        Line2D left = new Line2D(new Point2D(-1.0, 0.0), Point2D.Y_AXIS_DIR);
        Line2D right = new Line2D(new Point2D(1.0, 0.0), Point2D.Y_AXIS_DIR);
        Line2D top = new Line2D(new Point2D(0.0, 1.0), Point2D.X_AXIS_DIR);
        Line2D bottom = new Line2D(new Point2D(0.0, -1.0), Point2D.X_AXIS_DIR);

        Point2D leftIntersect = circle.getNearestLineIntersection(left);
        Point2D rightIntersect = circle.getNearestLineIntersection(right);
        Point2D topIntersect = circle.getNearestLineIntersection(top);
        Point2D bottomIntersect = circle.getNearestLineIntersection(bottom);

        assertEquals(-1.0, leftIntersect.getX(), 0.00001);
        assertEquals(0.0, leftIntersect.getY(), 0.00001);
        assertEquals(1.0, rightIntersect.getX(), 0.00001);
        assertEquals(0.0, rightIntersect.getY(), 0.00001);
        assertEquals(0.0, topIntersect.getX(), 0.00001);
        assertEquals(1.0, topIntersect.getY(), 0.00001);
        assertEquals(0.0, bottomIntersect.getX(), 0.00001);
        assertEquals(-1.0, bottomIntersect.getY(), 0.00001);
    }

    @Test
    public void testLineInsersectsCircleReturnsNearest() {
        Circle2D circle = new Circle2D(new Point2D(1.0, 1.0), 1.0);
        Line2D lineOriginatingInLowerLeftOfBounds = new Line2D(new Point2D(0.0, 0.0), new Point2D(1.0, 1.0));

        final double HYP = Math.sqrt(2.0) - 1;
        final double DIM = HYP * Math.cos(Math.PI * 0.25);
        Point2D nearestLineIntersection = circle.getNearestLineIntersection(lineOriginatingInLowerLeftOfBounds);
        assertEquals(DIM, nearestLineIntersection.getX(), 0.00001);
        assertEquals(DIM, nearestLineIntersection.getY(), 0.00001);

        Line2D lineOriginatingInUpperRightOfBounds = new Line2D(new Point2D(2.0, 2.0), new Point2D(1.0, 1.0));

        nearestLineIntersection = circle.getNearestLineIntersection(lineOriginatingInUpperRightOfBounds);
        assertEquals(2.0 - DIM, nearestLineIntersection.getX(), 0.00001);
        assertEquals(2.0 - DIM, nearestLineIntersection.getY(), 0.00001);
    }

    @Test
    public void testLineInsersectsCircleReturnsBothValues() {
        Circle2D circle = new Circle2D(new Point2D(1.0, 1.0), 1.0);
        Line2D lineOriginatingInLowerLeftOfBounds = new Line2D(new Point2D(0.0, 0.0), new Point2D(1.0, 1.0));

        final double HYP = Math.sqrt(2.0) - 1;
        final double DIM = HYP * Math.cos(Math.PI * 0.25);
        Set<Point2D> intersections = circle.getLineIntersectionPoints(lineOriginatingInLowerLeftOfBounds);
        Point2D p1 = new Point2D(DIM, DIM);
        Point2D p2 = new Point2D(2.0 - DIM, 2.0 - DIM);
        assertEquals(2, intersections.size());
        assertTrue(GeometryTestUtil.setContainsSimilarPoint(p1, intersections));
        assertTrue(GeometryTestUtil.setContainsSimilarPoint(p2, intersections));
    }
}
