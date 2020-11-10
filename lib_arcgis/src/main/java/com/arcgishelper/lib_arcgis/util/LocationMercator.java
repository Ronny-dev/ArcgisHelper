package com.arcgishelper.lib_arcgis.util;

/**
 * @author ：HYH on 2018/11/26 14:33
 * @description ：墨卡托坐标
 */
public class LocationMercator {

    public double x;
    public double y;

    public LocationMercator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
