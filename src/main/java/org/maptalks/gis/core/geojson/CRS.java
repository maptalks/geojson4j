package org.maptalks.gis.core.geojson;

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





}
