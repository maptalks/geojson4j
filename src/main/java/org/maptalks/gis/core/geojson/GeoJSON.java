package org.maptalks.gis.core.geojson;


import com.alibaba.fastjson.JSON;

public abstract class GeoJSON {
    private String type;
    private CRS crs;
    
    public GeoJSON() {
        setType(getClass().getSimpleName());
    }
    
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CRS getCrs() {
        return crs;
    }

    public void setCrs(CRS crs) {
        this.crs = crs;
    }
}
