package com.sportdataconnect.geometry;

import java.awt.Dimension;

/**
 * Representation of the extent of a rectangle in 2D space. The bounds are represented as a width and height (i.e. the
 * extent in the X and Y dimensions respectively).
 *
 * @author sportdataconnect
 */
public final class Extent2D {
    
    private double width;
    private double height;

    /**
     * Constructs a new Extent2D
     * @param width
     * @param height
     */
    public Extent2D(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the extent of the rectangle in the Y dimension
     * @return Returns the extent of the rectangle in the Y dimension
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the extent of the rectangle in the X dimension
     * @return Returns the extent of the rectangle in the X dimension
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the ratio of the width to the height of the rectangle
     * @return Returns the ratio of the width to the height of the rectangle
     */
    public double getAspectRatio() {
        return width / height;
    }

    @Override
    public String toString() {
        return "Extent2D{" + "width=" + width + ", height=" + height + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Extent2D other = (Extent2D) obj;
        if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
            return false;
        }
        if (Double.doubleToLongBits(this.height) != Double.doubleToLongBits(other.height)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        return hash;
    }
    
    /**
     * Converts this extent to an AWT dimension object using basic java double to int
     * conversion (i.e. truncation)
     * @return
     */
    public Dimension toDimension() {
        return new Dimension((int) width, (int) height);
    }
}
