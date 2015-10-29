package org.maptalks.gis.core.geojson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public abstract class GeoJSON {
    // default: 0
    @JSONField(ordinal = -1)
    private String type;
    private CRS crs;

    public GeoJSON() {
        setType(getClass().getSimpleName());
    }

    @Override
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
