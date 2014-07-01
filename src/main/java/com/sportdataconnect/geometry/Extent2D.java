package com.sportdataconnect.geometry;

import java.awt.Dimension;

/**
 *
 * @author sportdataconnect
 */
public final class Extent2D {
    
    private double width;
    private double height;

    public Extent2D(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    
    public double getAspectRatio() {
        return getWidth() / getHeight();
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
