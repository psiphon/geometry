package com.sportdataconnect.geometry;

/**
 * Representation of a rectangular bounding box in 2D space. The bounds are represented
 * by minimum and maximum values in the X and Y dimensions - i.e. the boundaries of the
 * rectangle are parallel to either the X or Y axis.
 *
 * @author sportdataconnect
 */
public final class Bounds2D {
    
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    public Bounds2D(final double minX, final double maxX, final double minY, final double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    /**
     * Returns whether or not the point described by the parameters is within the
     * bounding rectangle. Points exactly on the edge are considered to be within the
     * bounding rectangle.
     * @param x
     * @param y
     * @return Returns whether or not the point described by the parameters is within the
     * bounding rectangle
     */
    public boolean inBounds(final int x, final int y) {
        return inBounds(new Point2D(x, y));
    }
    
    /**
     * Returns whether or not the point is within the
     * bounding rectangle. Points exactly on the edge are considered to be within
     *  the bounding rectangle.
     * @param pt
     * @return Returns whether or not the point is within the
     * bounding rectangle.
     */
    public boolean inBounds(final Point2D pt) {
        return minX <= pt.getX() && pt.getX() <= maxX
                && minY <= pt.getY() && pt.getY() <= maxY;
    }
    
    /**
     * Returns the width of the bounding rectangle in the X dimension.
     * @return Returns the width of the bounding rectangle in the X dimension.
     */
    public double getWidth() {
        return maxX - minX;
    }
    
    /**
     * Returns the height of the bounding rectangle in the Y direction.
     * @return Returns the height of the bounding rectangle in the Y direction.
     */
    public double getHeight() {
        return maxY - minY;
    }
    
    /**
     * Returns the ratio of the width to the height of the bounding box.
     * @return Returns the ratio of the width to the height of the bounding box.
     */
    public double getAspectRatio() {
        return getWidth() / getHeight();
    }

    @Override
    public String toString() {
        return "Bounds2D{" + "minX=" + minX + ", maxX=" + maxX + ", minY=" + minY + ", maxY=" + maxY + '}';
    }

    /**
     * Returns the bounding rectangle translated by the specified distances in the X and Y coordinates.
     * @param dx
     * @param dy
     * @return Returns the bounding rectangle translated by the specified distances in the X and Y coordinates.
     */
    public Bounds2D translate(final double dx, final double dy) {
        return new Bounds2D(minX + dx, maxX + dx, minY + dy, maxY + dy);
    }

    /**
     * Returns the rectangle extended outwards by the specified amounts - assuming
     * to maximum Y value to correspond to the 'top' and the maximum X value to correspond to the 'right'.
     * @param leftExtension The minimum X value is reduced by this amount
     * @param rightExtension The maximum X value is increased by this amount
     * @param bottomExtension The minimum Y value is increased by this amount
     * @param topExtension The maximum Y value is increased by this amount
     * @return Returns the rectangle extended outwards by the specified amounts
     */
    public Bounds2D extend(final double leftExtension, final double rightExtension, final double bottomExtension,
            final double topExtension) {
        return new Bounds2D(minX - leftExtension, maxX + rightExtension, minY - bottomExtension, maxY + topExtension);
    }

    /**
     * Returns the bounding rectangle after having been scaled (or dilated) by the
     * specified factor from the origin
     * @param scale
     * @return Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor from the origin
     */
    public Bounds2D scale(final float scale) {
        return new Bounds2D(minX * scale, maxX * scale, minY * scale, maxY * scale);
    }

    /**
     * Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor from the origin
     * @param scale
     * @return Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor from the origin
     */
    public Bounds2D scale(final double scale) {
        return new Bounds2D(minX * scale, maxX * scale, minY * scale, maxY * scale);
    }
    
    /**
     * Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor from the specified centre of dilation.
     * @param scale
     * @param point
     * @return Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor from the specified centre of dilation.
     */
    public Bounds2D scaleFromPoint(final double scale, final Point2D point) {
        double newMinX = point.getX() + scale * (minX - point.getX());
        double newMinY = point.getY() + scale * (minY - point.getY());
        double newMaxX = point.getX() + scale * (maxX - point.getX());
        double newMaxY = point.getY() + scale * (maxY - point.getY());
        return new Bounds2D(newMinX, newMaxX, newMinY, newMaxY);
    }
    
    /**
     * Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor using it's centre as the centre of dilation.
     * @param scale
     * @return Returns the bounding rectangle after having been scaled (or dilated) by the specified
     * factor using it's centre as the centre of dilation.
     */
    public Bounds2D scaleFromCentre(final double scale) {
        return scaleFromPoint(scale, getCentre());
    }
    
    /**
     * Returns the centre of the bounding rectangle (i.e. the point that is halfway between the
     * minimum and maximum X values and halfway between the minimum and maximum Y values)
     * @return Returns the centre of the bounding rectangle
     */
    public Point2D getCentre() {
        return new Point2D((minX + maxX) * 0.5, (minY + maxY) * 0.5);
    }

    /**
     * Returns the bounding rectangle scaled (or dilated) only in the X direction - using the
     * specified x coordinate as the centre of dilation
     * @param scale
     * @param x
     * @return Returns the bounding rectangle scaled (or dilated) only in the X direction
     */
    public Bounds2D scaleHorizontally(final double scale, final double x) {
        
        double newMinX = x + scale * (minX - x);
        double newMinY = minY;
        double newMaxX = x + scale * (maxX - x);
        double newMaxY = maxY;
        return new Bounds2D(newMinX, newMaxX, newMinY, newMaxY);
    }

    /**
     * Returns the bounding rectangle that is the same size and shape as this one
     * but has it's centre at the origin.
     * @return Returns the bounding rectangle that is the same size and shape as this one
     * but has it's centre at the origin.
     */
    public Bounds2D translateToOrigin() {
        return new Bounds2D(0.0, this.getWidth(), 0.0, this.getHeight());
    }
    
    
}
