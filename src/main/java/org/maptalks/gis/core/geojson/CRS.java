package org.maptalks.gis.core.geojson;

import com.alibaba.fastjson.JSON;
import org.maptalks.gis.core.geojson.common.exceptions.InvalidCRSException;

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

    //some common used CRS definitions
    public final static CRS WGS84 = CRS.createProj4("+proj=longlat +datum=WGS84 +no_defs");
    public final static CRS EPSG4326 = WGS84;
    public final static CRS EPSG3857 = CRS.createProj4("+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +wktext  +no_defs");
    //crs for plane coordinate systems, like indoor map or pixel based scenarios.
    public final static CRS IDENTITY = CRS.createProj4("+proj=identity");
    //official coordinate system in China, in most cases, it can be considered same with wgs84
    //http://spatialreference.org/ref/sr-org/7408/
    public final static CRS CGCS2000 = CRS.createProj4("+proj=longlat +datum=CGCS2000");
    //crs usded in Chinese map services due to the coordinate encryption.
    //https://en.wikipedia.org/wiki/Restrictions_on_geographic_data_in_China
    public final static CRS BD09LL = CRS.createProj4("+proj=longlat +datum=BD09");
    public final static CRS GCJ02 = CRS.createProj4("+proj=longlat +datum=GCJ02");

    public final static CRS DEFAULT = WGS84;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CRS)) {
            return false;
        }
        CRS o = ((CRS) obj);
        return this.type.equals(o.type) && this.properties.equals(o.properties);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * generate a crs from json string
     * @param json
     * @return
     */
    public static CRS parseJson(String json) {
        if (json == null || json.length() ==0) {
            return null;
        }
        try {
            return JSON.parseObject(json, CRS.class);
        }catch (Throwable e) {
            throw new InvalidCRSException(json,e);
        }
    }

    /**
     * create a proj4 style CRS
     * @param proj  proj4 string
     * @return      proj4 style crs
     */
    public static CRS createProj4(final String proj) {
        CRS crs = new CRS();
        crs.setType("proj4");
        crs.setProperties(new HashMap<String, Object>() {
            {
                put("proj", proj);
            }
        });
        return crs;
    }

    public static String getProj4(CRS crs) {
        if (crs == null || crs.getProperties() == null) {
            return null;
        }
        return crs.getProperties().get("proj").toString();
    }

}
