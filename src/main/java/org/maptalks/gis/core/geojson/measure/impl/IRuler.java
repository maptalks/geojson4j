package org.maptalks.gis.core.geojson.measure.impl;

/**
 * 定义一个坐标系对应的空间计算方法
 * Created by fuzhen on 2015/7/2.
 */
public interface IRuler {
    /**
     * 计算距离src距离为xDistance，yDistance的点的坐标
     * @param src
     * @param xDistance
     * @param yDistance
     * @return
     */
    double[] locate(double[] src, double xDistance,
                    double yDistance);

    /**
     * 计算两个坐标之间的长度
     * @param mlonlat1
     * @param mlonlat2
     * @return
     */
    double computeGeodesicLength(final double[] mlonlat1,
                                 final double[] mlonlat2);

    /**
     * 计算坐标数组的图形面积
     * @param rings
     * @return
     */
    double computeArea(double[][] rings);

    /**
     * 计算坐标数组的总长度
     * @param rings
     * @return
     */
    double computeGeodesicLength(double[][] rings);
}
