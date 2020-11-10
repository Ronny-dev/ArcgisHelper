package com.arcgishelper.lib_arcgis.util;

import com.arcgishelper.lib_arcgis.entity.MapCoordinateSystem;

/**
 * Created by Ronny on 2020/11/10
 */
public class LocationCoordinateUtils {

    public static MapCoordinateSystem MAP_COORDINATE_SYSTEM = MapCoordinateSystem.GCJ02;

    public static double a = 6378245.0D;
    public static double ee = 0.006693421622965943D;

    /**
     * 从Gps84经纬度转墨卡托到地图
     *
     * @param lon
     * @param lat
     * @return
     */
    public static LocationMercator gps84ToMapMercator(double lon, double lat) {
        LocationCoordinate latLngInfo;
        switch (MAP_COORDINATE_SYSTEM) {
            case GCJ02:
                //gps84转gcj再转墨卡托
                latLngInfo = LocationCoordinateUtils.gps84_To_Gcj02(lat, lon);
                return lonLatToMercator(latLngInfo.longitude, latLngInfo.latitude);
            case BD09:
                //百度地图
                // Map<String, Double> stringDoubleMap = Baidu.convertLL2MC(lon, lat);
                latLngInfo = LocationCoordinateUtils.gps84_To_Gcj02(lat, lon);
                LocationCoordinate coordinate = LocationCoordinateUtils.gcj02_To_Bd09(latLngInfo.latitude, latLngInfo.longitude);
                return lonLatToMercator(coordinate.longitude, coordinate.latitude);
            //return new LocationMercator(stringDoubleMap.get("y"),stringDoubleMap.get("x"));
            default:
                return lonLatToMercator(lon, lat);
        }
    }

    /**
     * 坐标（谷歌、高德是gcj,opencyclemap是gps）转墨卡托
     *
     * @param lon
     * @param lat
     * @return
     */
    private static LocationMercator lonLatToMercator(double lon, double lat) {
        double toX = lon * 20037508.34D / 180.0D;
        double toY = Math.log(Math
                .tan((90.0D + lat) * 3.141592653589793D / 360.0D)) / 0.0174532925199433D;
        toY = toY * 20037508.34D / 180.0D;

        return new LocationMercator(toX, toY);
    }

    private static LocationCoordinate gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double x = gg_lon;
        double y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 2.E-005D
                * Math.sin(y * 3.141592653589793D);
        double theta = Math.atan2(y, x) + 3.E-006D
                * Math.cos(x * 3.141592653589793D);
        double bd_lon = z * Math.cos(theta) + 0.0065D;
        double bd_lat = z * Math.sin(theta) + 0.006D;
        return new LocationCoordinate(bd_lat, bd_lon);
    }

    private static LocationCoordinate gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new LocationCoordinate(lat, lon);
        }
        double dLat = transformLat(lon - 105.0D, lat - 35.0D);
        double dLon = transformLon(lon - 105.0D, lat - 35.0D);
        double radLat = lat / 180.0D * 3.141592653589793D;
        double magic = Math.sin(radLat);
        magic = 1.0D - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = dLat * 180.0D
                / (a * (1.0D - ee) / (magic * sqrtMagic) * 3.141592653589793D);
        dLon = dLon * 180.0D
                / (a / sqrtMagic * Math.cos(radLat) * 3.141592653589793D);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new LocationCoordinate(mgLat, mgLon);
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0D + 2.0D * x + 3.0D * y + 0.2D * y * y + 0.1D * x
                * y + 0.2D * Math.sqrt(Math.abs(x));

        ret += (20.0D * Math.sin(6.0D * x * 3.141592653589793D) + 20.0D * Math
                .sin(2.0D * x * 3.141592653589793D)) * 2.0D / 3.0D;
        ret += (20.0D * Math.sin(y * 3.141592653589793D) + 40.0D * Math
                .sin(y / 3.0D * 3.141592653589793D)) * 2.0D / 3.0D;
        ret += (160.0D * Math.sin(y / 12.0D * 3.141592653589793D) + 320.0D * Math
                .sin(y * 3.141592653589793D / 30.0D)) * 2.0D / 3.0D;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0D + x + 2.0D * y + 0.1D * x * x + 0.1D * x * y + 0.1D
                * Math.sqrt(Math.abs(x));

        ret += (20.0D * Math.sin(6.0D * x * 3.141592653589793D) + 20.0D * Math
                .sin(2.0D * x * 3.141592653589793D)) * 2.0D / 3.0D;
        ret += (20.0D * Math.sin(x * 3.141592653589793D) + 40.0D * Math
                .sin(x / 3.0D * 3.141592653589793D)) * 2.0D / 3.0D;
        ret += (150.0D * Math.sin(x / 12.0D * 3.141592653589793D) + 300.0D * Math
                .sin(x / 30.0D * 3.141592653589793D)) * 2.0D / 3.0D;

        return ret;
    }

    public static boolean outOfChina(double lat, double lon) {
        if ((lon < 72.004000000000005D) || (lon > 137.8347D))
            return true;
        if ((lat < 0.8293D) || (lat > 55.827100000000002D))
            return true;
        return false;
    }
}
