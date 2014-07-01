package com.sportdataconnect.geometry;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author sportdataconnect
 */
public class Triangle2DTest {
    
    @Test
    public void testContains() {
        Triangle2D triangle = new Triangle2D(
                new Point2D(0.0, 0.0),
                new Point2D(1.0, 0.0),
                new Point2D(0.5, 1.0));
        assertTrue(triangle.contains(new Point2D(0.5, 0.5)));
        assertFalse(triangle.contains(new Point2D(1.0, 2.0)));
    }
    
}
