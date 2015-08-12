package org.maptalks.gis.core.geojson.ext;

import java.util.Arrays;

/**
 * 椭圆形
 * Created by fuzhen on 2015/8/11.
 */
public class Ellipse extends GeometryExt {
    private double[] coordinates;
    private double width;
    private double height;

    public Ellipse(double[] coordinates, double width, double height) {
        this(coordinates,width,height,Unit.Default);
    }

    public Ellipse(double[] coordinates, double width, double height, Unit unit) {
        super(unit.toString());
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
    }

    public Ellipse() {
        super(Unit.Default.toString());
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
        if (!(obj instanceof Ellipse)) {
            return false;
        }
        Ellipse o = ((Ellipse) obj);
        return Arrays.equals(this.coordinates, o.coordinates) && this.width == o.width && this.height == o.height && this.getUnit().equals(o.getUnit());
    }
}
