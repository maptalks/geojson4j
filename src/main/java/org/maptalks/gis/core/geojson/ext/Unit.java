package org.maptalks.gis.core.geojson.ext;

/**
 * 长度单位
 * Created by fuzhen on 2015/8/11.
 */
public enum Unit {
    Default("Meter"),
    //米
    Meter("Meter"),
    //像素
    PX("PX");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return this.unit;
    }

}
