package org.maptalks.gis.core.geojson.ext;

import java.util.Arrays;

/**
 * 圆形
 * Created by fuzhen on 2015/8/11.
 */
public class Circle extends GeometryExt {
    private double[] coordinates;
    private double radius;

    public Circle(double[] coordinates, double radius) {
        super(Unit.Default.toString());
        this.coordinates = coordinates;
        this.radius = radius;
    }

    public Circle(double[] coordinates, double radius, Unit unit) {
        super(unit.toString());
        this.coordinates = coordinates;
        this.radius = radius;
    }

    public Circle() {
        super(Unit.Default.toString());
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Circle)) {
            return false;
        }
        Circle o = ((Circle) obj);
        return Arrays.equals(this.coordinates, o.coordinates) && this.radius == o.radius && this.getUnit().equals(o.getUnit());
    }
}
