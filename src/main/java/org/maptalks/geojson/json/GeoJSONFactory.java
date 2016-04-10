package org.maptalks.geojson.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.maptalks.geojson.*;
import org.maptalks.geojson.ext.Circle;
import org.maptalks.geojson.ext.Ellipse;
import org.maptalks.geojson.ext.Rectangle;
import org.maptalks.geojson.ext.Sector;

import java.util.HashMap;
import java.util.Map;

public class GeoJSONFactory {
    static Map<String, Class> geoJsonTypeMap = new HashMap<String, Class>();

    static {
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_POINT,Point.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_LINESTRING,LineString.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_POLYGON,Polygon.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_MULTIPOINT,MultiPoint.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_MULTILINESTRING,MultiLineString.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_MULTIPOLYGON,MultiPolygon.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_GEOMETRYCOLLECTION,GeometryCollection.class);
        //extended geojson types
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_EXT_CIRCLE,Circle.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_EXT_ELLIPSE,Ellipse.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_EXT_RECTANGLE,Rectangle.class);
        geoJsonTypeMap.put(GeoJSONTypes.TYPE_EXT_SECTOR,Sector.class);
    }

    public static GeoJSON create(String json) {
        JSONObject node = JSON.parseObject(json);
        return create(node);
    }

    /**
     * 将json解析为FeatureCollection数组
     * @param json
     * @return
     */
    public static FeatureCollection[] createFeatureCollectionArray(String json) {
        JSONArray node = JSON.parseArray(json);
        int size = node.size();
        FeatureCollection[] result = new FeatureCollection[size];
        for (int i = 0; i < size; i++) {
            if (node.get(i) == null) {
                result[i] = null;
                continue;
            }
            result[i] = ((FeatureCollection) create(((JSONObject) node.get(i))));
        }
        return result;
    }

    /**
     * 将json解析为Feature数组
     * @param json
     * @return
     */
    public static Feature[] createFeatureArray(String json) {
        JSONArray node = JSON.parseArray(json);
        int size = node.size();
        Feature[] result = new Feature[size];
        for (int i = 0; i < size; i++) {
            if (node.get(i) == null) {
                result[i] = null;
                continue;
            }
            GeoJSON geojson = create(((JSONObject) node.get(i)));
            if (geojson instanceof Geometry) {
                result[i] = new Feature(((Geometry) geojson));
            } else {
                result[i] = ((Feature) geojson);
            }
        }
        return result;
    }


    /**
     * 将json解析为Geometry数组
     * @param json
     * @return
     */
    public static Geometry[] createGeometryArray(String json) {
        JSONArray node = JSON.parseArray(json);

        int size = node.size();
        Geometry[] result = new Geometry[size];
        for (int i = 0; i < size; i++) {
            if (node.get(i) == null) {
                result[i] = null;
                continue;
            }
            GeoJSON geojson = create(((JSONObject) node.get(i)));
            if (geojson instanceof Feature) {
                result[i] = ((Feature) geojson).getGeometry();
            } else {
                result[i] = ((Geometry) geojson);
            }
        }
        return result;
    }

    private static GeoJSON create(JSONObject node) {
        String type = node.getString("type");
        if ("FeatureCollection".equals(type)) {
            return readFeatureCollection(node);
        } else if ("Feature".equals(type)) {
            return readFeature(node);
        } else {
            return readGeometry(node, type);
        }
    }

    private static FeatureCollection readFeatureCollection(JSONObject node) {

        JSONArray jFeatures = node.getJSONArray("features");
        if (jFeatures == null) {
            //return a empty FeatureCollection
            FeatureCollection result = new FeatureCollection();
            result.setFeatures(new Feature[0]);
            return result;
        }
        int size = jFeatures.size();
        Feature[] features = new Feature[size];
        for (int i=0;i<size;i++) {
            features[i] = readFeature(jFeatures.getJSONObject(i));
        }
        FeatureCollection result = new FeatureCollection();
        result.setFeatures(features);
        return result;
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
        if (GeoJSONTypes.TYPE_GEOMETRYCOLLECTION.equals(type)) {
            GeometryCollection result = new GeometryCollection(new Geometry[0]);
            JSONArray jGeos = node.getJSONArray("geometries");
            if (jGeos != null) {
                int size = jGeos.size();
                Geometry[] geometries = new Geometry[size];
                for (int i=0;i<size;i++) {
                    JSONObject jgeo = jGeos.getJSONObject(i);
                    geometries[i] = readGeometry(jgeo, jgeo.getString("type"));
                }
                result.setGeometries(geometries);
            } else {
                //return a empty GeometryCollection
                result.setGeometries(new Geometry[0]);
            }
            return result;
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
