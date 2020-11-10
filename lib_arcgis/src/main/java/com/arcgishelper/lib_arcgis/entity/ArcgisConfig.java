package com.arcgishelper.lib_arcgis.entity;

import android.os.Environment;

/**
 * Created by Ronny on 2020/11/10
 */
public class ArcgisConfig {

    private MapServiceProvider mapServiceProvider = MapServiceProvider.AMAP;

    private MapLayerType mapLayerType = MapLayerType.VECTOR;

    private String locationPath = Environment.getExternalStoragePublicDirectory("arcgis").getAbsolutePath();

    private int defaultMapScale = 10000;

    private int defaultMapMaxScale = 800;

    private double defaultMapCenterLatitude = 23.0d;

    private double defaultMapCenterLongitude = 113.0d;

    public MapServiceProvider getMapServiceProvider() {
        return mapServiceProvider;
    }

    public void setMapServiceProvider(MapServiceProvider mapServiceProvider) {
        this.mapServiceProvider = mapServiceProvider;
    }

    public MapLayerType getMapLayerType() {
        return mapLayerType;
    }

    public void setMapLayerType(MapLayerType mapLayerType) {
        this.mapLayerType = mapLayerType;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public int getDefaultMapScale() {
        return defaultMapScale;
    }

    public void setDefaultMapScale(int defaultMapScale) {
        this.defaultMapScale = defaultMapScale;
    }

    public int getDefaultMapMaxScale() {
        return defaultMapMaxScale;
    }

    public void setDefaultMapMaxScale(int defaultMapMaxScale) {
        this.defaultMapMaxScale = defaultMapMaxScale;
    }

    public double getDefaultMapCenterLatitude() {
        return defaultMapCenterLatitude;
    }

    public void setDefaultMapCenterLatitude(double defaultMapCenterLatitude) {
        this.defaultMapCenterLatitude = defaultMapCenterLatitude;
    }

    public double getDefaultMapCenterLongitude() {
        return defaultMapCenterLongitude;
    }

    public void setDefaultMapCenterLongitude(double defaultMapCenterLongitude) {
        this.defaultMapCenterLongitude = defaultMapCenterLongitude;
    }
}
