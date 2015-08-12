package org.maptalks.gis.core.geojson;


import java.util.Arrays;

public class Point extends Geometry {
    private double[] coordinates;

    public Point() {
        super();
    }

    public Point(double [] coordinates) {
        super();
        this.setCoordinates(coordinates);
    }


    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point o = ((Point) obj);
        return Arrays.equals(this.coordinates, o.coordinates);
    }
}
