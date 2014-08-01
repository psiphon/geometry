package com.sportdataconnect.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Representation of a 2D shape extruded into 3D space along a central edge. The extrusion consists of a 2D polygon
 * describing the cross section and 3D start and end points. From this description the start and end face can be
 * described as points, edges or triangles. The supplied cross section polygon need not be projected in a plane
 * perpendicular to the central edge - the face dir parameters allows the cross section to be projected onto an
 * arbitrary plane.
 *
 * @author sportdataconnect
 */
public final class Extrusion3D {
    
    private Point3D startPoint;
    private Point3D endPoint;
    private Point3D faceDirX;
    private Point3D faceDirY;
    
    private Polygon2D facePoints;

    public Extrusion3D(final Point3D startPoint, final Point3D endPoint, final Point3D faceDirX, final Point3D faceDirY,
            final Polygon2D facePoints) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.faceDirX = faceDirX;
        this.faceDirY = faceDirY;
        this.facePoints = facePoints;
    }

    public Point3D getEndPoint() {
        return endPoint;
    }

    public Point3D getFaceDirX() {
        return faceDirX;
    }

    public Point3D getFaceDirY() {
        return faceDirY;
    }

    public Point3D getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the all edges along the start face of the extrusion.
     * @return Returns the all edges along the start face of the extrusion
     */
    public List<Edge3D> getStartFaceEdges() {
        List<Edge3D> result = new ArrayList<Edge3D>(facePoints.numPoints());
        for (int i = 0; i < facePoints.numPoints() - 1; i++) {
            result.add(new Edge3D(calculateStartFacePoint(i), calculateStartFacePoint(i + 1)));
        }
        result.add(new Edge3D(calculateStartFacePoint(facePoints.numPoints() - 1), calculateStartFacePoint(0)));
        return result;
    }

    /**
     * Returns the all edges along the end face of the extrusion with the direction of each edge reversed (the
     * order of the edge set is preserved from the order of points in the original polygon).
     * @return Returns the all edges along the start face of the extrusion
     */
    public List<Edge3D> getReversedEndFaceEdges() {
        List<Edge3D> result = new ArrayList<Edge3D>(facePoints.numPoints());
        for (int i = 0; i < facePoints.numPoints() - 1; i++) {
            result.add(new Edge3D(calculateEndFacePoint(i + 1), calculateEndFacePoint(i)));
        }
        result.add(new Edge3D(calculateEndFacePoint(0), calculateEndFacePoint(facePoints.numPoints() - 1)));
        return result;
    }

    /**
     * Returns the start face broken down into triangles. The tesselation of the 3D triangles is derived from the
     * tesselation of the originally supplied 2D polygon.
     * @return Returns the start face broken down into triangles
     */
    public Collection<Triangle3D> getStartFaceTriangles() {
        Collection<Triangle2D> sfc = facePoints.toTriangles();
        Collection<Triangle3D> result = new ArrayList<Triangle3D>(sfc.size());
        for (Triangle2D sfcTriangle : sfc) {
            result.add(startFaceUnprojector.unproject(sfcTriangle));
        }
        return result;
    }

    /**
     * Returns the start face broken down into triangles. The tesselation of the 3D triangles is derived from the
     * tesselation of the originally supplied 2D polygon.
     * @return Returns the start face broken down into triangles
     */
    public Collection<Triangle3D> getEndFaceTriangles() {
        Collection<Triangle2D> efc = facePoints.toTriangles();
        Collection<Triangle3D> result = new ArrayList<Triangle3D>(efc.size());
        for (Triangle2D efcTriangle : efc) {
            result.add(endFaceUnprojector.unproject(efcTriangle));
        }
        return result;
    }
    
    private Unprojector startFaceUnprojector = new Unprojector() {

        public Point3D unproject(final Point2D sfpt) {
            return startPoint
                .add(faceDirX.scale(sfpt.getX()))
                .add(faceDirY.scale(sfpt.getY()));
        }
    };
    
    private Unprojector endFaceUnprojector = new Unprojector() {

        public Point3D unproject(final Point2D sfpt) {
            return endPoint
                .add(faceDirX.scale(sfpt.getX()))
                .add(faceDirY.scale(sfpt.getY()));
        }
    };
    
    private Point3D calculateStartFacePoint(final int ixStartFace) {
        return startFaceUnprojector.unproject(facePoints.get(ixStartFace));
    }
    
    private Point3D calculateEndFacePoint(final int ixEndFace) {
        return endFaceUnprojector.unproject(facePoints.get(ixEndFace));
    }
    
}
