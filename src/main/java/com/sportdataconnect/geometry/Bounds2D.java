package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public final class Bounds2D {
    
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

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

    public boolean inBounds(final int x, final int y) {
        return inBounds(new Point2D(x, y));
    }
    
    public boolean inBounds(final Point2D pt) {
        return minX <= pt.getX() && pt.getX() <= maxX
                && minY <= pt.getY() && pt.getY() <= maxY;
    }
    
    public double getWidth() {
        return maxX - minX;
    }
    
    public double getHeight() {
        return maxY - minY;
    }
    
    public double getAspectRatio() {
        return getWidth() / getHeight();
    }

    @Override
    public String toString() {
        return "Bounds2D{" + "minX=" + minX + ", maxX=" + maxX + ", minY=" + minY + ", maxY=" + maxY + '}';
    }

    public Bounds2D translate(final double dx, final double dy) {
        return new Bounds2D(minX + dx, maxX + dx, minY + dy, maxY + dy);
    }

    public Bounds2D extend(final double leftExtension, final double rightExtension, final double bottomExtension,
            final double topExtension) {
        return new Bounds2D(minX - leftExtension, maxX + rightExtension, minY - bottomExtension, maxY + topExtension);
    }

    public Bounds2D scale(final float scale) {
        return new Bounds2D(minX * scale, maxX * scale, minY * scale, maxY * scale);
    }

    public Bounds2D scale(final double scale) {
        return new Bounds2D(minX * scale, maxX * scale, minY * scale, maxY * scale);
    }
    
    public Bounds2D scaleFromPoint(final double scale, final Point2D point) {
        double newMinX = point.getX() + scale * (minX - point.getX());
        double newMinY = point.getY() + scale * (minY - point.getY());
        double newMaxX = point.getX() + scale * (maxX - point.getX());
        double newMaxY = point.getY() + scale * (maxY - point.getY());
        return new Bounds2D(newMinX, newMaxX, newMinY, newMaxY);
    }
    
    public Bounds2D scaleFromCentre(final double scale) {
        return scaleFromPoint(scale, getCentre());
    }
    
    public Point2D getCentre() {
        return new Point2D((minX + maxX) * 0.5, (minY + maxY) * 0.5);
    }

    public Bounds2D scaleHorizontally(final double scale, final double x) {
        
        double newMinX = x + scale * (minX - x);
        double newMinY = minY;
        double newMaxX = x + scale * (maxX - x);
        double newMaxY = maxY;
        return new Bounds2D(newMinX, newMaxX, newMinY, newMaxY);
    }

    public Bounds2D translateToOrigin() {
        return new Bounds2D(0.0, this.getWidth(), 0.0, this.getHeight());
    }
    
    
}
