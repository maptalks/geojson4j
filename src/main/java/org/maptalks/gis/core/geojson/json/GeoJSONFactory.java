package org.maptalks.gis.core.geojson.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.maptalks.gis.core.geojson.*;
import org.maptalks.gis.core.geojson.ext.Circle;
import org.maptalks.gis.core.geojson.ext.Ellipse;
import org.maptalks.gis.core.geojson.ext.Rectangle;
import org.maptalks.gis.core.geojson.ext.Sector;

import java.util.HashMap;
import java.util.Map;

public class GeoJSONFactory {
    static Map<String, Class> geoJsonTypeMap = new HashMap<String, Class>();

    static {
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_POINT,Point.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_LINESTRING,LineString.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_POLYGON,Polygon.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_MULTIPOINT,MultiPoint.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_MULTILINESTRING,MultiLineString.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_MULTIPOLYGON,MultiPolygon.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_GEOMETRYCOLLECTION,GeometryCollection.class);
        //extended geojson types
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_EXT_CIRCLE,Circle.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_EXT_ELLIPSE,Ellipse.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_EXT_RECTANGLE,Rectangle.class);
        geoJsonTypeMap.put(GeoJsonTypes.TYPE_EXT_SECTOR,Sector.class);
    }

    public static GeoJSON create(String json) {
        JSONObject node = JSON.parseObject(json);
        return create(node);
    }

    public static Feature[] createFeatureArray(String json) {
        JSONArray node = JSON.parseArray(json);

        int size = node.size();
        Feature[] result = new Feature[size];
        for (int i = 0; i < size; i++) {
            result[i] = ((Feature) create(((JSONObject) node.get(i))));
        }
        return result;
    }

    private static GeoJSON create(JSONObject node) {
        String type = node.getString("type");
        if (type.equals("FeatureCollection")) {
            return readFeatureCollection(node);
        } else if (type.equals("Feature")) {
            return readFeature(node);
        } else {
            return readGeometry(node, type);
        }
    }
    
    private static FeatureCollection readFeatureCollection(JSONObject node) {
        JSONArray jFeatures = node.getJSONArray("features");
        if (jFeatures == null) {
            //return a empty FeatureCollection
            return new FeatureCollection(new Feature[0]);
        }
        int size = jFeatures.size();
        Feature[] features = new Feature[size];
        for (int i=0;i<size;i++) {
            features[i] = readFeature(jFeatures.getJSONObject(i));
        }
        return new FeatureCollection(features);
    }
    
    private static Feature readFeature(JSONObject node) {
        JSONObject geoJ = node.getJSONObject("geometry");
        Geometry geo = null;
        if (geoJ != null) {
            geo = readGeometry(geoJ, geoJ.getString("type"));
        }
        node.remove("geometry");
        Feature result = JSON.toJavaObject(node, Feature.class);
        result.setGeometry(geo);
        return result;
    }
    
    private static Geometry readGeometry(JSONObject node, String type) {
        if (GeoJsonTypes.TYPE_GEOMETRYCOLLECTION.equals(type)) {
            JSONArray jGeos = node.getJSONArray("geometries");
            if (jGeos != null) {
                int size = jGeos.size();
                Geometry[] geometries = new Geometry[size];
                for (int i=0;i<size;i++) {
                    JSONObject jgeo = jGeos.getJSONObject(i);
                    geometries[i] = readGeometry(jgeo, jgeo.getString("type"));
                }
                return new GeometryCollection(geometries);
            }
            //return a empty GeometryCollection
            return new GeometryCollection(new Geometry[0]);
        }
        Class clazz = getGeoJsonType(type);
        Object obj = JSON.toJavaObject(node, clazz);
        return ((Geometry) obj);
    }

    public static Class getGeoJsonType(String type) {
        Class clazz = geoJsonTypeMap.get(type);
        return clazz;
    }
}
