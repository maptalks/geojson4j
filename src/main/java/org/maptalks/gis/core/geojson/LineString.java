package org.maptalks.gis.core.geojson;

import java.util.Arrays;

public class LineString extends Geometry {
    private double[][] coordinates;

    public LineString() {
        super();
    }

    public LineString(double [][] coordinates) {
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
        if (!(obj instanceof LineString)) {
            return false;
        }
        LineString o = ((LineString) obj);
        return Arrays.deepEquals(this.coordinates, o.coordinates);
    }
}
