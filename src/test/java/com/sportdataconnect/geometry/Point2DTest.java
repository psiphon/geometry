package com.sportdataconnect.geometry;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author sportdataconnect
 */
public class Point2DTest {
    
    @Test
    public void testFirstQuadrantPolarAngle() {
        Point2D pt = new Point2D(1.0, 1.0);
        assertEquals(Math.PI * 0.25, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testSecondQuadrantPolarAngle() {
        Point2D pt = new Point2D(-1.0, 1.0);
        assertEquals(Math.PI * 0.75, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testThirdQuadrantPolarAngle() {
        Point2D pt1 = new Point2D(-1.0, -1.0);
        assertEquals(Math.PI * 1.25, pt1.polarAngle(), 0.00001);
        Point2D pt2 = new Point2D(-1.0, -2.0);
        assertEquals(4.24874, pt2.polarAngle(), 0.00001);
    }
    
    @Test
    public void testFourthQuadrantPolarAngle() {
        Point2D pt = new Point2D(1.0, -1.0);
        assertEquals(Math.PI * 1.75, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testPositiveXAxisPolarAngle() {
        Point2D pt = new Point2D(1.0, 0.0);
        assertEquals(0.0, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testNegativeXAxisPolarAngle() {
        Point2D pt = new Point2D(-1.0, 0.0);
        assertEquals(Math.PI, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testPositiveYAxisPolarAngle() {
        Point2D pt = new Point2D(0.0, 1.0);
        assertEquals(Math.PI * 0.5, pt.polarAngle(), 0.00001);
    }
    
    @Test
    public void testNegativeYAxisPolarAngle() {
        Point2D pt = new Point2D(0.0, -1.0);
        assertEquals(Math.PI * 1.5, pt.polarAngle(), 0.00001);
    }
    
}
