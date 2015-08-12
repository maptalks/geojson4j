package org.maptalks.gis.core.geojson.ext;

import java.util.Arrays;

/**
 * 扇形
 * Created by fuzhen on 2015/8/11.
 */
public class Sector extends Circle {
    //开始角,单位为度
    private int startAngle;
    //结束角,单位为度
    private int endAngle;

    public Sector(double[] coordinates, double radius, int startAngle, int endAngle) {
       this(coordinates, radius, startAngle,endAngle, Unit.Default);
    }

    public Sector(double[] coordinates, double radius, int startAngle, int endAngle, Unit unit) {
        super(coordinates,radius, unit);
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    public Sector() {
        super();
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public int getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(int endAngle) {
        this.endAngle = endAngle;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sector)) {
            return false;
        }
        Sector o = ((Sector) obj);
        return Arrays.equals(this.getCoordinates(), o.getCoordinates()) && this.getRadius() == o.getRadius()
                && this.startAngle == o.startAngle && this.endAngle == o.endAngle && this.getUnit().equals(o.getUnit());
    }
}
