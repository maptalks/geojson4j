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
        this.closeRing();
    }

    private void closeRing() {
        if (coordinates == null) {
            return;
        }
        for (int i = 0; i < coordinates.length; i++) {
            double[][][] polygon = coordinates[i];
            if (polygon != null) {
                for (int i1 = 0; i1 < polygon.length; i1++) {
                    double[][] ring = polygon[i1];
                    if (ring != null && ring.length>0) {
                        if (!Arrays.equals(ring[0], ring[ring.length - 1])) {
                            double[][] close = new double[ring.length+1][];
                            System.arraycopy(ring,0,close,0,ring.length);
                            close[close.length-1] = ring[0];
                            polygon[i1] = close;
                        }
                    }
                }
            }
        }
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
