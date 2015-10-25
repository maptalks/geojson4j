package org.maptalks.gis.core.geojson.common.convertor.impl;


import org.maptalks.gis.core.geojson.common.CoordinateType;
import org.maptalks.gis.core.geojson.common.convertor.ICoordTypeConvertor;

public class Gcj02_Wgs84_BD09Convertor implements ICoordTypeConvertor {


    /**
     * 坐标转换
     *
     * @param coordinate
     * @param from
     * @param to
     * @return
     */
    @Override
    public double[] convert(double[] coordinate, CoordinateType from,
                            CoordinateType to) {
        if (from == null || to == null)
            return null;
        if (from.equals(to))
            return coordinate;
        if ((CoordinateType.wgs84.equals(from) || CoordinateType.cgcs2000.equals(from))
                && CoordinateType.gcj02.equals(to)) {
            return LonLatConvertor.wgs2GCJ(coordinate[0],
                    coordinate[1]);
        } else if (CoordinateType.gcj02.equals(from) &&
            (CoordinateType.wgs84.equals(to) || CoordinateType.cgcs2000.equals(to))) {
             /*return CoordinateConvertor.gcj2WGSExactly(coordinate[0],
                coordinate[1]);*/
            return LonLatConvertor.gcj2WGS(coordinate[0],
                    coordinate[1]);
        } else if (CoordinateType.bd09ll.equals(from)
            && CoordinateType.gcj02.equals(to)) {
            return LonLatConvertor.bd092GCJ(coordinate[0], coordinate[1]);
        } else if (CoordinateType.gcj02.equals(from)
            && CoordinateType.bd09ll.equals(to)) {
            return LonLatConvertor.gcj2BD09(coordinate[0], coordinate[1]);
        } else if ((CoordinateType.wgs84.equals(from) || CoordinateType.cgcs2000.equals(from))
                && CoordinateType.bd09ll.equals(to)) {
            return LonLatConvertor.wgs2BD09(coordinate[0],
                    coordinate[1]);
        } else if (CoordinateType.bd09ll.equals(from) &&
            (CoordinateType.wgs84.equals(to) || CoordinateType.cgcs2000.equals(to))) {
            return LonLatConvertor.bd092WGS(coordinate[0],
                    coordinate[1]);
        }else {
            throw new IllegalArgumentException("illegal coordinate type:"+from+","+to);
        }
    }

    public static void main(final String[] args) {
        /*double[][] lonlats = new double[][] {
                {114.694904,33.639097},

                {114.694886,33.638049},

                {114.695007,33.637943},

                {114.695784,33.63794},

                {114.695928,33.637966},

                {114.697513,33.637958}
        };*/

        double[][] lonlats = new double[][]{
                {114.68837663801743, 33.63312016454496},
                {114.68835840204522, 33.632072446353945},
                {114.68848002806972, 33.63196427051657},
                {114.68926112541861, 33.63194729708501},
                {114.68940588838505, 33.6319707051534},
                {114.69099925796665, 33.63193416046613}
        };

        ICoordTypeConvertor convertor = new Gcj02_Wgs84_BD09Convertor();

        for (int i = 0; i < lonlats.length; i++) {
            double[] to = convertor.convert(lonlats[i], CoordinateType.gcj02, CoordinateType.wgs84);
            System.out.println("[" + to[0] + "," + to[1] + "],");
        }
    }

    /**
     * 坐标转换程序
     *
     *  WGS84坐标系：即地球坐标系，国际上通用的坐标系。Earth

     GCJ02坐标系：即火星坐标系，WGS84坐标系经加密后的坐标系。Mars

     BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系。  Bd09

     搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
     *
     *   百度地图API        百度坐标
     腾讯搜搜地图API 火星坐标
     搜狐搜狗地图API 搜狗坐标*
     阿里云地图API     火星坐标
     图吧MapBar地图API 图吧坐标
     高德MapABC地图API 火星坐标
     灵图51ditu地图API 火星坐标
     *
     * @author fankun
     * @link http://my.oschina.net/fankun2013/blog/338100
     */
    private static class LonLatConvertor {
        private static  double PI      = Math.PI;
        private static  double AXIS            = 6378245.0;  //
        private static  double OFFSET          = 0.00669342162296594323;  //(a^2 - b^2) / a^2
        private static  double X_PI     = PI * 3000.0 / 180.0;

        //GCJ-02=>BD09 火星坐标系=>百度坐标系
        public static double[] gcj2BD09(double glon, double glat){
            double x = glon;
            double y = glat;
            double[] lonlat = new double[2];
            double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * X_PI);
            double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * X_PI);
            lonlat[1] = z * Math.sin(theta) + 0.006;
            lonlat[0] = z * Math.cos(theta) + 0.0065;
            return lonlat;
        }

        //BD09=>GCJ-02 百度坐标系=>火星坐标系
        public static double[] bd092GCJ(double glon, double glat){
            double x = glon - 0.0065;
            double y = glat - 0.006;
            double[] lonlat = new double[2];
            double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
            double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
            lonlat[1] = z * Math.sin(theta);
            lonlat[0] = z * Math.cos(theta);
            return lonlat;
        }
        //BD09=>WGS84 百度坐标系=>地球坐标系
        public static double[] bd092WGS(double glon, double glat){
            double[] lonlat = bd092GCJ(glon,glat);
            return gcj2WGS(lonlat[0],lonlat[1]);
        }
        // WGS84=》BD09   地球坐标系=>百度坐标系
        public static double[] wgs2BD09(double wgLon, double wgLat) {
            double[] lonlat = wgs2GCJ(wgLon,wgLat);
            return gcj2BD09(lonlat[0],lonlat[1]);
        }

        // WGS84=》GCJ02   地球坐标系=>火星坐标系
        public static double[] wgs2GCJ(double wgLon, double wgLat) {
            double[] lonlat  = new double[2];
            if (outOfChina(wgLon, wgLat)){
                lonlat[1] = wgLat;
                lonlat[0] = wgLon;
                return lonlat;
            }
            double[] deltaD =  delta(wgLon,wgLat);
            lonlat[1] = wgLat + deltaD[1];
            lonlat[0] = wgLon + deltaD[0];
            return lonlat;
        }
        //GCJ02=>WGS84   火星坐标系=>地球坐标系(粗略)
        public static double[] gcj2WGS(double glon,double glat){
            double[] lonlat  = new double[2];
            if (outOfChina(glon, glat)){
                lonlat[1] = glat;
                lonlat[0] = glon;
                return lonlat;
            }
            double[] deltaD =  delta(glon,glat);
            lonlat[1] = glat - deltaD[1];
            lonlat[0] = glon - deltaD[0];
            return lonlat;
        }
        //GCJ02=>WGS84   火星坐标系=>地球坐标系（精确）
        public static double[] gcj2WGSExactly(double gcjLon,double gcjLat){
            double initDelta = 0.01;
            double threshold = 0.000000001;
            double dLat = initDelta, dLon = initDelta;
            double mLat = gcjLat - dLat, mLon = gcjLon - dLon;
            double pLat = gcjLat + dLat, pLon = gcjLon + dLon;
            double wgsLat, wgsLon, i = 0;
            while (true) {
                wgsLat = (mLat + pLat) / 2;
                wgsLon = (mLon + pLon) / 2;
                double[] tmp = wgs2GCJ(wgsLon, wgsLat);
                dLat = tmp[1] - gcjLat;
                dLon = tmp[0] - gcjLon;
                if ((Math.abs(dLat) < threshold) && (Math.abs(dLon) < threshold))
                    break;

                if (dLat > 0) pLat = wgsLat; else mLat = wgsLat;
                if (dLon > 0) pLon = wgsLon; else mLon = wgsLon;

                if (++i > 10000) break;
            }
            double[] lonlat = new double[2];
            lonlat[1] = wgsLat;
            lonlat[0] = wgsLon;
            return lonlat;
        }

        //两点距离
        public static double distance(double logA, double latA, double logB,double  latB){
            int earthR = 6371000;
            double x = Math.cos(latA*Math.PI/180) * Math.cos(latB*Math.PI/180) * Math.cos((logA-logB)*Math.PI/180);
            double y = Math.sin(latA*Math.PI/180) * Math.sin(latB*Math.PI/180);
            double s = x + y;
            if (s > 1)
                s = 1;
            if (s < -1)
                s = -1;
            double alpha = Math.acos(s);
            double distance = alpha * earthR;
            return distance;
        }

        public static double[] delta(double wgLon, double wgLat){
            double[] lngLat  = new double[2];
            double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
            double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
            double radLat = wgLat / 180.0 * PI;
            double magic = Math.sin(radLat);
            magic = 1 - OFFSET * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((AXIS * (1 - OFFSET)) / (magic * sqrtMagic) * PI);
            dLon = (dLon * 180.0) / (AXIS / sqrtMagic * Math.cos(radLat) * PI);
            lngLat[1] =dLat;
            lngLat[0] =dLon;
            return lngLat;
        }

        public static boolean outOfChina(double lon, double lat){
            if (lon < 72.004 || lon > 137.8347)
                return true;
            if (lat < 0.8293 || lat > 55.8271)
                return true;
            return false;
        }

        public static double transformLat(double x, double y){
            double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
            ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
            ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
            ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
            return ret;
        }

        public static double transformLon(double x, double y){
            double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
            ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
            ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
            ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
            return ret;
        }
    }
}
