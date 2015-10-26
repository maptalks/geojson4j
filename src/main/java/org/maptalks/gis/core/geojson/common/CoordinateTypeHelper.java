package org.maptalks.gis.core.geojson.common;

import org.maptalks.gis.core.geojson.CRS;
import org.maptalks.gis.core.geojson.GeoJSON;

/**
 * Created by fuzhen on 2015/10/26.
 */
public class CoordinateTypeHelper {
    /**
     * 获取GeoJson的CoordinateType
     * @param geoJson
     * @return
     */
    public static CoordinateType getCoordinateTypeFromGeoJson(GeoJSON geoJson) {
        if (geoJson.getCrs() == null || geoJson.getCrs().getProperties() == null) {
            return null;
        }
        if ("cnCoordinateType".equals(geoJson.getCrs().getType())) {
            Object s = geoJson.getCrs().getProperties().get("name");
            if (s != null) {
                return CoordinateType.getInstance(s.toString());
            }
        }
        return null;
    }

    /**
     * 设置GeoJson为指定的CoordinateType
     * @param geoJson
     */
    public static void setCoordinateTypeToGeoJson(GeoJSON geoJson,CoordinateType coordinateType) {
        CRS crs = CRS.createCnCoordinateType(coordinateType);
        geoJson.setCrs(crs);
    }
}
