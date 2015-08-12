package org.maptalks.gis.core.geojson;


import java.util.Arrays;

public class Polygon extends Geometry {
    private double[][][] coordinates;

    public Polygon() {
        super();
    }

    public Polygon(double [][][] coordinates) {
        super();
        this.setCoordinates(coordinates);
    }

    public double[][][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polygon)) {
            return false;
        }
        Polygon o = ((Polygon) obj);
        return Arrays.deepEquals(this.coordinates, o.coordinates);
    }
}
