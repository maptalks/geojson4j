package org.maptalks.gis.core.geojson.common;

import org.maptalks.gis.core.geojson.CRS;
import org.maptalks.gis.core.geojson.GeoJSON;

/**
 * Created by fuzhen on 2015/10/26.
 */
public class CoordinateTypeHelper {

    /**
     * 将coordinateType转化为crs
     * @param coordinateType
     * @return
     */
    public static CRS convertToCRS(CoordinateType coordinateType) {
        if (coordinateType == null) {
            return null;
        }
        CRS crs = CRS.createCnCoordinateType(coordinateType);
        return crs;
    }

    /**
     * 从crs中读取coordinateType
     * @param crs
     * @return
     */
    public static CoordinateType convertFromCRS(CRS crs) {
        if (crs == null) {
            return null;
        }
        if ("cnCoordinateType".equals(crs.getType())) {
            Object s = crs.getProperties().get("name");
            if (s != null) {
                return CoordinateType.getInstance(s.toString());
            }
        }
        return null;
    }

}
