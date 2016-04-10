package org.maptalks.geojson;


import java.util.Arrays;

public class MultiPoint extends Geometry {
    private double[][] coordinates;

    public MultiPoint() {
        super();
    }

    public MultiPoint( double [][] coordinates) {
        super();
        this.setCoordinates(coordinates);
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MultiPoint)) {
            return false;
        }
        MultiPoint o = ((MultiPoint) obj);
        return Arrays.deepEquals(this.coordinates,o.coordinates);
    }
}
