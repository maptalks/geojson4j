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
        this.closeRing();
    }

    private void closeRing() {
        if (coordinates == null) {
            return;
        }
        for (int i = 0; i < coordinates.length; i++) {
            double[][] ring = coordinates[i];
            if (ring != null && ring.length>0) {
                if (!Arrays.equals(ring[0], ring[ring.length - 1])) {
                    double[][] close = new double[ring.length+1][];
                    System.arraycopy(ring,0,close,0,ring.length);
                    close[close.length-1] = ring[0];
                    coordinates[i] = close;
                }
            }
        }
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
