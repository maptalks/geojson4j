package org.maptalks.gis.core.geojson.common.convertor;


import org.maptalks.gis.core.geojson.CRS;

/**
 * Created by fuzhen on 2015/8/12.
 */
public interface ICoordTypeConvertor {
    double[] convert(double[] coordinate, CRS from,
                     CRS to);
}
