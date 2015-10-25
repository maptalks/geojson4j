package org.maptalks.gis.core.geojson.ext;

/**
 * 长度单位
 * Created by fuzhen on 2015/8/11.
 */
public enum Unit {

    //米
    Meter("Meter"),
    //像素
    PX("PX");

    public final static Unit Default = Unit.Meter;

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return this.unit;
    }

}
