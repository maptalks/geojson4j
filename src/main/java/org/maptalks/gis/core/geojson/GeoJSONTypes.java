package org.maptalks.gis.core.geojson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuzhen on 2015/8/11.
 */
public class GeoJSONTypes {
    public final static String TYPE_POINT="Point";
    public final static String TYPE_LINESTRING="LineString";
    public final static String TYPE_POLYGON="Polygon";
    public final static String TYPE_MULTIPOINT="MultiPoint";
    public final static String TYPE_MULTILINESTRING="MultiLineString";
    public final static String TYPE_MULTIPOLYGON="MultiPolygon";
    public final static String TYPE_GEOMETRYCOLLECTION="GeometryCollection";
    //extended geojson types
    public static final String TYPE_EXT_CIRCLE = "Circle";
    public static final String TYPE_EXT_ELLIPSE = "Ellipse";
    public static final String TYPE_EXT_RECTANGLE = "Rectangle";
    public static final String TYPE_EXT_SECTOR = "Sector";



    //int value of geoJson types, identical with WKB.
    public final static int INT_TYPE_POINT=1;
    public final static int INT_TYPE_LINESTRING=2;
    public final static int INT_TYPE_POLYGON=3;
    public final static int INT_TYPE_MULTIPOINT=4;
    public final static int INT_TYPE_MULTILINESTRING=5;
    public final static int INT_TYPE_MULTIPOLYGON=6;
    public final static int INT_TYPE_GEOMETRYCOLLECTION=7;
    //extended geojson types
    public static final int INT_TYPE_EXT_CIRCLE = 101;
    public static final int INT_TYPE_EXT_ELLIPSE = 102;
    public static final int INT_TYPE_EXT_RECTANGLE = 103;
    public static final int INT_TYPE_EXT_SECTOR = 104;


    private static final Map<String, Integer> typeMaps = new HashMap<String, Integer>();
    private static final Map<Integer,String> rTypeMaps = new HashMap<Integer,String>();

    static  {
        typeMaps.put(TYPE_POINT, INT_TYPE_POINT);
        typeMaps.put(TYPE_LINESTRING, INT_TYPE_LINESTRING);
        typeMaps.put(TYPE_POLYGON, INT_TYPE_POLYGON);
        typeMaps.put(TYPE_MULTIPOINT, INT_TYPE_MULTIPOINT);
        typeMaps.put(TYPE_MULTILINESTRING, INT_TYPE_MULTILINESTRING);
        typeMaps.put(TYPE_MULTIPOLYGON, INT_TYPE_MULTIPOLYGON);
        typeMaps.put(TYPE_GEOMETRYCOLLECTION, INT_TYPE_GEOMETRYCOLLECTION);
        //-----
        typeMaps.put(TYPE_EXT_CIRCLE, INT_TYPE_EXT_CIRCLE);
        typeMaps.put(TYPE_EXT_ELLIPSE, INT_TYPE_EXT_ELLIPSE);
        typeMaps.put(TYPE_EXT_RECTANGLE, INT_TYPE_EXT_RECTANGLE);
        typeMaps.put(TYPE_EXT_SECTOR, INT_TYPE_EXT_SECTOR);

        for (Map.Entry<String, Integer> entry : typeMaps.entrySet()) {
            rTypeMaps.put(entry.getValue(), entry.getKey());
        }
    }

    public static String stringValueOf(int type) {
        return rTypeMaps.get(type);
    }

    public static int intValueOf(String type) {
        return typeMaps.get(type);
    }
}
