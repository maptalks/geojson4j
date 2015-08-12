package org.maptalks.gis.core.geojson.ext;

import org.maptalks.gis.core.geojson.Geometry;

/**
 * 扩展Geometry图形, 增加了Unit定义
 * Created by fuzhen on 2015/8/11.
 */
public class GeometryExt extends Geometry {
    //长度单位, meter或者px
    private String unit;

    public GeometryExt(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
