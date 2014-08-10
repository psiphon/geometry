package com.sportdataconnect.geometry;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CreateConvexPolygonTest {


    @Test
    public void testTriangle() {
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(1.0, 0.0);
        Point2D p3 = new Point2D(1.0, 1.0);

        final ImmutableList<Point2D> points = ImmutableList.of(p1, p2, p3);
        final List<Point2D> convexPolygon = Point2D.createConvexPolygon(points);
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p1, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p2, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p3, convexPolygon));
    }

    @Test
    public void testPointWithinTriangle() {
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(1.0, 0.0);
        Point2D p3 = new Point2D(1.0, 1.0);
        Point2D p4 = new Point2D(0.25, 0.125);

        final ImmutableList<Point2D> points = ImmutableList.of(p1, p2, p3, p4);
        final List<Point2D> convexPolygon = Point2D.createConvexPolygon(points);
        assertEquals(3, convexPolygon.size());
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p1, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p2, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p3, convexPolygon));
    }

    @Test
    public void testPointWithinQuad() {
        Point2D p1 = new Point2D(-2.0, -2.0);
        Point2D p2 = new Point2D(1.0, -2.0);
        Point2D p3 = new Point2D(1.0, 1.0);
        Point2D p4 = new Point2D(-2.0, 1.0);
        Point2D p5 = new Point2D(0.0, 0.0);

        final ImmutableList<Point2D> points = ImmutableList.of(p1, p2, p3, p4);
        final List<Point2D> convexPolygon = Point2D.createConvexPolygon(points);
        assertEquals(4, convexPolygon.size());
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p1, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p2, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p3, convexPolygon));
        assertTrue(GeometryTestUtil.collectionContainsSimilarPoints(p4, convexPolygon));
    }
}
