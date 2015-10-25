package org.maptalks.gis.core.geojson.common.convertor;


import org.maptalks.gis.core.geojson.common.CoordinateType;

/**
 * Created by fuzhen on 2015/8/12.
 */
public interface ICoordTypeConvertor {
    double[] convert(double[] SCoordinate, CoordinateType from,
                     CoordinateType to);
}
