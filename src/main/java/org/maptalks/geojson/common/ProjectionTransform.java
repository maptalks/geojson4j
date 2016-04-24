package org.maptalks.geojson.common;

import org.maptalks.geojson.*;
import org.maptalks.proj4.Proj4;
import org.maptalks.proj4.Proj4Exception;

public class ProjectionTransform {

    public static Geometry transform(Geometry geometry, CRS f, CRS t) throws Proj4Exception {
        if (f == null || t == null) {
            return geometry;
        }
        if (f.equals(t)) {
            return geometry;
        }

        // XXX: currently only support proj4 style crs
        if (!f.getType().equals("proj4") || !t.getType().equals("proj4")) {
            return geometry;
        }

        String fp = (String) f.getProperties().get("proj");
        String tp = (String) t.getProperties().get("proj");
        Proj4 proj4 = new Proj4(fp, tp);

        return transform(geometry, proj4);
    }

    public static Geometry transform(Geometry geometry, Proj4 proj4) throws Proj4Exception {
        if (geometry instanceof Point) {
            double[] c = convert(((Point) geometry).getCoordinates(), proj4);
            return new Point(c);
        } else if (geometry instanceof LineString) {
            double[][] c = convert(((LineString) geometry).getCoordinates(), proj4);
            return new LineString(c);
        } else if (geometry instanceof Polygon) {
            double[][][] c = convert(((Polygon) geometry).getCoordinates(), proj4);
            return new Polygon(c);
        } else if (geometry instanceof MultiPoint) {
            double[][] c = convert(((MultiPoint) geometry).getCoordinates(), proj4);
            return new MultiPoint(c);
        } else if (geometry instanceof MultiLineString) {
            double[][][] c = convert(((MultiLineString) geometry).getCoordinates(), proj4);
            return new MultiLineString(c);
        } else if (geometry instanceof MultiPolygon) {
            double[][][][] c = convert(((MultiPolygon) geometry).getCoordinates(), proj4);
            return new MultiPolygon(c);
        } else if (geometry instanceof GeometryCollection) {
            Geometry[] geometries = ((GeometryCollection) geometry).getGeometries();
            if (geometries != null) {
                int size = geometries.length;
                Geometry[] array = new Geometry[size];
                for (int i = 0; i < size; i++) {
                    array[i] = transform(geometries[i], proj4);
                }
                return new GeometryCollection(array);
            }
            return new GeometryCollection();
        }
        return null;
    }

    public static double[][][][] convert(double[][][][] coordinates, Proj4 proj4) throws Proj4Exception {
        if (coordinates == null) {
            return null;
        }
        if (coordinates.length == 0) {
            return new double[0][0][0][0];
        }
        double[][][][] result = new double[coordinates.length][][][];
        for (int i = 0; i < coordinates.length; i++) {
            result[i] = convert(coordinates[i], proj4);
        }
        return result;
    }

    public static double[][][] convert(double[][][] coordinates, Proj4 proj4) throws Proj4Exception {
        if (coordinates == null) {
            return null;
        }
        if (coordinates.length == 0) {
            return new double[0][0][0];
        }
        double[][][] result = new double[coordinates.length][][];
        for (int i = 0; i < coordinates.length; i++) {
            result[i] = convert(coordinates[i], proj4);
        }
        return result;
    }

    public static double[][] convert(double[][] coordinates, Proj4 proj4) throws Proj4Exception {
        if (coordinates == null || coordinates.length == 0) {
            return null;
        }
        double[][] result = new double[coordinates.length][];
        for (int i = 0, len = coordinates.length; i < len; i++) {
            double[] coordinate = coordinates[i];
            double[] converted = convert(coordinate, proj4);
            if (converted != null) {
                result[i] = converted;
            } else {
                result[i] = coordinates[i];
            }
        }
        return result;
    }

    public static double[] convert(double[] coordinate, Proj4 proj4) throws Proj4Exception {
        return proj4.forward(coordinate);
    }

}
