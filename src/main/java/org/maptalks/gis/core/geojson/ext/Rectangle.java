package org.maptalks.gis.core.geojson.ext;

import java.util.Arrays;

/**
 * 用左上角+长度+宽度, 定义的矩形
 * Created by fuzhen on 2015/8/11.
 */
public class Rectangle extends GeometryExt {
    private double[] coordinates;
    private double width;
    private double height;

    public Rectangle(double[] coordinates, double width, double height) {
        this(coordinates,width,height,Unit.Default);
    }

    public Rectangle(double[] coordinates, double width, double height, Unit unit) {
        super(unit);
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        super(Unit.Default);
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        Rectangle o = ((Rectangle) obj);
        return Arrays.equals(this.coordinates, o.coordinates) && this.width == o.width && this.height == o.height && this.getUnit().equals(o.getUnit());
    }
}
