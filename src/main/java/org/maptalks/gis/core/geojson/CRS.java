package org.maptalks.gis.core.geojson;

import org.maptalks.gis.core.geojson.common.CoordinateType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuzhen on 2015/10/6.
 */
public class CRS {
    private String type;
    private Map<String,Object> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public final static CRS DEFAULT;

    static {
        DEFAULT = new CRS();
        DEFAULT.setType("EPSG");
        DEFAULT.setProperties(new HashMap<String, Object>(){
            {put("code","4326");}
        });
    }

    /**
     * create a Chinese Coordinate Type CRS.
     * example:
     * "crs": {"type": "cnCoordinateType","properties": {"name": "gcj02"}}
     * possible values: gcj02, cgcs2000, bd09ll, wgs84
     * @param coordinateType
     * @return
     */
    public static CRS createCnCoordinateType(final CoordinateType coordinateType) {
        CRS crs = new CRS();
        crs.setType("cnCoordinateType");
        crs.setProperties(new HashMap<String, Object>(){
            {
                put("name", coordinateType.toString());
            }
        });
        return crs;
    }

    /**
     * create a standard CRS.
     * this is recommended by geojson.org
     * @link http://geojson.org/geojson-spec.html#named-crs
     * example:
     * "crs": {"type": "name","properties": {"name": "urn:ogc:def:crs:OGC:1.3:CRS84"}}
     * @param name
     * @return
     */
    public static CRS createCRS(final String name) {
        CRS crs = new CRS();
        crs.setType("name");
        crs.setProperties(new HashMap<String, Object>(){
            {
                put("name", name);
            }
        });
        return crs;
    }

    /**
     * create a EPSG style CRS recommended by spatialreference.org.
     * @link http://spatialreference.org/ref/epsg/4326/json/
     * example:
     * {'type': 'EPSG', 'properties': {'code': 4326}}
     * @param code
     * @return
     */
    public static CRS createEPSGCRS(final String code) {
        CRS crs = new CRS();
        crs.setType("EPSG");
        crs.setProperties(new HashMap<String, Object>(){
            {
                put("code", code);
            }
        });
        return crs;
    }


}
