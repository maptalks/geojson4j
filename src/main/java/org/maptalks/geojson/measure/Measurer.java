package org.maptalks.geojson.measure;

import org.maptalks.geojson.*;
import org.maptalks.geojson.measure.impl.IRuler;
import org.maptalks.geojson.measure.impl.IdentityRuler;
import org.maptalks.geojson.measure.impl.SphericalRuler;

/**
 * Created by fuzhen on 2015/7/2.
 */
public class Measurer{
    private static IRuler sphericalRuler = new SphericalRuler();
    private static IRuler identityRuler = new IdentityRuler();

    private static IRuler getRuler(CRS crs) {
        if (crs.getProperties() != null && CRS.IDENTITY.getProperties().get("proj").toString().equalsIgnoreCase(crs.getProperties().get("proj").toString())) {
            return identityRuler;
        }
        return sphericalRuler;
    }

    /**
     * 计算距离src坐标x轴, y轴距离的点的坐标
     * @param src
     * @param x
     * @param y
     * @param crs
     * @return
     */
    public static double[] locate(double[] src, double x, double y, CRS crs) {
        IRuler ruler = getRuler(crs);
        return ruler.locate(src, x, y);
    }

    /**
     * 计算两点间距离
     * @param p1
     * @param p2
     * @param crs
     * @return
     */
    public static double computeLength(double[] p1, double[] p2, CRS crs) {
        IRuler ruler = getRuler(crs);
        return ruler.computeGeodesicLength(p1, p2);
    }

    /**
     * 计算多点围成的面积
     * @param ring
     * @param crs
     * @return
     */
    public static double computeArea(double[][] ring, CRS crs) {
        IRuler ruler = getRuler(crs);
        return ruler.computeArea(ring);
    }

    /**
     * 计算多个点构成的线段长度
     * @param path
     * @param crs
     * @return
     */
    public static double computeLength(double[][] path, CRS crs) {
        IRuler ruler = getRuler(crs);
        return ruler.computeGeodesicLength(path);
    }

    /**
     * 计算二维数组点的总长度
     * @param coordinates
     * @param crs
     * @return
     */
    public static double computeLength(double[][][] coordinates, CRS crs) {
        if (coordinates == null || coordinates.length == 0) {
            return 0;
        }
        double result = 0;
        for (int i = 0; i < coordinates.length; i++) {
            result += computeLength(coordinates[i], crs);
        }
        return result;
    }

    /**
     * 计算三维数组点的总长度
     * @param coordinates
     * @param crs
     * @return
     */
    public static double computeLength(double[][][][] coordinates, CRS crs) {
        if (coordinates == null || coordinates.length == 0) {
            return 0;
        }
        double result = 0;
        for (int i = 0; i < coordinates.length; i++) {
            result += computeLength(coordinates[i],crs);
        }
        return result;
    }

    /**
     * 计算GeoJson图形的长度
     * @param geoJSON
     * @param crs
     * @return
     */
    public static double computeLength(GeoJSON geoJSON, CRS crs) {
        if (geoJSON == null) {
            return 0;
        }
        if (geoJSON instanceof FeatureCollection) {
            Feature[] features = ((FeatureCollection) geoJSON).getFeatures();
            double result = 0;
            for (int i = features.length - 1; i >= 0; i--) {
                result += computeLength(features[i], crs);
            }
            return result;
        }
        IRuler ruler = getRuler(crs);

        if (geoJSON instanceof Feature) {
            geoJSON = ((Feature) geoJSON).getGeometry();
            if (geoJSON == null) {
                return 0;
            }
        }

        String type = geoJSON.getType();

        switch (GeoJSONTypes.intValueOf(type)) {
            case GeoJSONTypes.INT_TYPE_LINESTRING:
                return ruler.computeGeodesicLength(((LineString) geoJSON).getCoordinates());
            case GeoJSONTypes.INT_TYPE_POLYGON:
                return computeLength(((Polygon) geoJSON).getCoordinates(), crs);

            case GeoJSONTypes.INT_TYPE_MULTIPOLYGON:
                return computeLength(((MultiPolygon) geoJSON).getCoordinates(),crs);
            case GeoJSONTypes.INT_TYPE_MULTILINESTRING:
                return computeLength(((MultiLineString) geoJSON).getCoordinates(), crs);
            case GeoJSONTypes.INT_TYPE_GEOMETRYCOLLECTION:
                double result = 0;
                Geometry[] geometries = ((GeometryCollection) geoJSON).getGeometries();
                if (geometries != null && geometries.length > 0) {
                    for (int i = 0; i < geometries.length; i++) {
                        result += computeLength(geometries[i],crs);
                    }
                }
                return result;
        }
        return 0;
    }


    /**
     * 计算GeoJSON图形的面积
     * @param geoJSON
     * @param crs
     * @return
     */
    public static double computeArea(GeoJSON geoJSON, CRS crs) {
        if (geoJSON == null) {
            return 0;
        }
        if (geoJSON instanceof FeatureCollection) {
            Feature[] features = ((FeatureCollection) geoJSON).getFeatures();
            double result = 0;
            for (int i = features.length - 1; i >= 0; i--) {
                result += computeArea(features[i],crs);
            }
            return result;
        }

        if (geoJSON instanceof Feature) {
            geoJSON = ((Feature) geoJSON).getGeometry();
            if (geoJSON == null) {
                return 0;
            }
        }

        String type = geoJSON.getType();

        switch (GeoJSONTypes.intValueOf(type)) {
            case GeoJSONTypes.INT_TYPE_POLYGON:
                return computePolygonArea(((Polygon) geoJSON).getCoordinates(), crs);
            case GeoJSONTypes.INT_TYPE_MULTIPOLYGON: {
                double result = 0;
                double[][][][] multiRings = ((MultiPolygon) geoJSON).getCoordinates();
                if (multiRings != null) {
                    for (int i = 0, len = multiRings.length; i < len; i++) {
                        result += computePolygonArea(multiRings[i], crs);
                    }
                }
                return result;
            }
            case GeoJSONTypes.INT_TYPE_GEOMETRYCOLLECTION: {
                double result = 0;
                Geometry[] geometries = ((GeometryCollection) geoJSON).getGeometries();
                if (geometries != null && geometries.length > 0) {
                    for (int i = 0; i < geometries.length; i++) {
                        result += computeArea(geometries[i],crs);
                    }
                }
                return result;
            }
        }

        return 0;
    }

    /**
     * 计算多边形rings数组形成的图形面积
     * @param rings rings数组,其中第一个元素是外环, 接下来的是内部空洞
     * @param crs
     * @return
     */
    private static double computePolygonArea(double[][][] rings, CRS crs) {
        if (rings == null || rings.length == 0){
            return 0;
        }

        double result = computeArea(rings[0], crs);
        if (rings.length>1) {
            for (int i = 1, len = rings.length; i < len; i++) {
                result -= computeArea(rings[i],crs);
            }
        }
        return result;
    }

}
