package org.maptalks.gis.core.geojson.common;

import org.maptalks.gis.core.geojson.common.exceptions.InvalidCoordinateTypeException;

/**
 * 坐标种类枚举类, 定义了中国地区普遍使用的类WGS84坐标类型, 涉及到的坐标类型如下:
 *
 *WGS84坐标系：即地球坐标系，国际上通用的坐标系。

 CGCS2000坐标系: 中国最新的官方坐标系, 与WGS84误差很小, 一般基本等同于WGS84

 GCJ02坐标系：即火星坐标系，WGS84坐标系经加密后的坐标系。

 BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系。

 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
 *
 * 各服务所用坐标系如下:
 *   天地图(测绘局官方服务)  CGCS2000
 *   百度地图API         BD09LL
     腾讯搜搜地图API      GCJ02
     搜狐搜狗地图API      搜狗坐标*
     阿里云地图API       GCJ02
     图吧MapBar地图API   图吧坐标*
     高德MapABC地图API   GCJ02
     灵图51ditu地图API   GCJ02

 * Created by fuzhen on 2015/8/11.
 */
public enum CoordinateType {
    wgs84("wgs84"), gcj02("gcj02"), cgcs2000("cgcs2000"), bd09ll("bd09ll"), pixel("pixel");

    public final static CoordinateType DEFAULT = gcj02;

    private final String type;

    CoordinateType(String type) {
        this.type=type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    /**
     * 获取CoordinateType
     * @param t
     * @return
     */
    public static CoordinateType getInstance(String t) {
        if (t == null || t.trim().length() == 0) {
            return null;
        }
        try {
            return CoordinateType.valueOf(t);
        } catch (Throwable e) {
            throw new InvalidCoordinateTypeException(t);
        }
    }

}
