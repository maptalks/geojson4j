package org.maptalks.gis.core.geojson;


import java.util.Arrays;

public class MultiPolygon extends Geometry {
    private  double[][][][] coordinates;

    public MultiPolygon() {
        super();
    }

    public MultiPolygon(double [][][][] coordinates) {
        super();
        this.setCoordinates(coordinates);
    }

    public double[][][][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][][][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MultiPolygon)) {
            return false;
        }
        MultiPolygon o = ((MultiPolygon) obj);
        return Arrays.deepEquals(this.coordinates,o.coordinates);
    }
}
