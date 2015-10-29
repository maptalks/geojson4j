package org.maptalks.gis.core.geojson.measure.impl;


/**
 * Created by fuzhen on 2015/7/2.
 */
public class SphericalRuler implements IRuler {

    public double[] locate(double[] src, double xDistance,
                              double yDistance) {
        double dx = Math.abs(xDistance);
        double dy = Math.abs(yDistance);
        double ry = this.rad(src[1]);
        double rx = this.rad(src[0]);
        double sy = Math.sin(dy / (2 * 6378137)) * 2;
        ry = ry + sy * (yDistance > 0 ? 1 : -1);
        double sx = 2 * Math.sqrt(Math.pow(Math.sin(dx / (2 * 6378137)), 2)
                / Math.pow(Math.cos(ry), 2));
        rx = rx + sx * (xDistance > 0 ? 1 : -1);
        if (Double.isNaN(rx) || Double.isNaN(ry)) {
            return null;
        }
        return new double[]{rx * 180 / Math.PI, ry * 180 / Math.PI};
    }


    public double computeArea(double[][] rings) {
        double a = 6378137 * Math.PI / 180, b = 0;
        if (rings.length < 3) {
            return 0d;
        }
        for (int i = 0, len = rings.length; i < len - 1; i++) {
            double[] e = rings[i], f = rings[i + 1];
            b += e[0]
                    * a
                    * Math.cos(e[1] * Math.PI / 180)
                    * f[1]
                    * a
                    - f[0]
                    * a
                    * Math.cos(f[1] * Math.PI / 180)
                    * e[1]
                    * a;
        }
        double[] d = rings[rings.length - 1];
        double[] c = rings[0];
        b += d[0]
                * a
                * Math.cos(d[1] * Math.PI / 180)
                * c[1]
                * a
                - c[0]
                * a
                * Math.cos(c[1] * Math.PI / 180)
                * d[1]
                * a;
        return 0.5 * Math.abs(b);
    }


    /**
     * 计算两经纬度之间的距离
     * @param mlonlat1
     * @param mlonlat2
     * @return
     */

    public double computeGeodesicLength(final double[] mlonlat1,
                                        final double[] mlonlat2) {
        double b = rad(mlonlat1[1]);
        final double d = rad(mlonlat2[1]), e = b - d, f = rad(mlonlat1
                [0]) - rad(mlonlat2[0]);
        b = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(e / 2), 2)
                + Math.cos(b)
                * Math.cos(d)
                * Math.pow(Math.sin(f / 2), 2)));
        b *= 6378137;
        return b = Math.round(b * 1E4) / 1E4;
    }


    public double computeGeodesicLength(double[][] rings) {
        if (rings == null || rings.length == 0) {
            return 0;
        }
        double result = 0;
        for (int i = 0, len = rings.length - 1; i < len; i++) {
            result += this.computeGeodesicLength(rings[i], rings[i + 1]);
        }
        return result;
    }

    /**
     * 计算弧度
     * @param a
     * @return
     */
    private double rad(final double a) {
        return a * Math.PI / 180;
    };
}
