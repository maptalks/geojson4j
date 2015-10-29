package org.maptalks.gis.core.geojson.measure.impl;

/**
 * Created by fuzhen on 2015/7/2.
 */
public class PixelRuler implements IRuler {

    public double[] locate(double[] src, double xDistance, double yDistance) {
        return new double[]{src[0] + xDistance, src[1] + yDistance};
    }

    public double computeGeodesicLength(double[] mlonlat1, double[] mlonlat2) {
        return 0;
    }

    public double computeArea(double[][] rings) {
        return 0;
    }

    public double computeGeodesicLength(double[][] rings) {
        return 0;
    }
}
