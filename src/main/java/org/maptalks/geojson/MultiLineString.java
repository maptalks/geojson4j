package org.maptalks.geojson;


import java.util.Arrays;

public class MultiLineString extends Geometry {
    private double[][][] coordinates;

    public MultiLineString() {
        super();
    }

    public MultiLineString(double [][][] coordinates) {
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
        if (!(obj instanceof MultiLineString)) {
            return false;
        }
        MultiLineString o = ((MultiLineString) obj);
        return Arrays.deepEquals(this.coordinates, o.coordinates);
    }
}
