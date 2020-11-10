package com.arcgishelper.lib_arcgis;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.arcgishelper.lib_arcgis.adapter.TileFactory;
import com.arcgishelper.lib_arcgis.adapter.TiledMapLayer;
import com.arcgishelper.lib_arcgis.entity.ArcgisConfig;
import com.arcgishelper.lib_arcgis.entity.MapLayerInfo;
import com.arcgishelper.lib_arcgis.util.LocationCoordinateUtils;
import com.arcgishelper.lib_arcgis.util.LocationMercator;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

/**
 * Created by Ronny on 2020/11/10
 */
public class ArcgisMapView extends FrameLayout {

    public ArcgisMapView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ArcgisMapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ArcgisMapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private MapView mMapView;

    private ArcgisConfig mConfig = new ArcgisConfig();

    private ArcGISMap mArcGISMap = new ArcGISMap(SpatialReferences.getWebMercator());

    public void buildMap() {
        mArcGISMap.setBasemap(getBaseTileLayer(mConfig));
        mMapView.setMap(mArcGISMap);
        centerAtDefaultConfig(mConfig);
    }

    public ArcgisMapView setConfig(ArcgisConfig config) {
        this.mConfig = config;
        return this;
    }

    /**
     * Config arcgis base tiles
     * @param config
     * @return
     */
    private Basemap getBaseTileLayer(ArcgisConfig config) {
        MapLayerInfo mapLayerInfo = TileFactory
                .createLayerInfo(config.getMapServiceProvider(), config.getMapLayerType());
        TiledMapLayer layer = new TiledMapLayer(mapLayerInfo, config.getLocationPath());
        layer.loadAsync();
        return new Basemap(layer);
    }

    private void centerAtDefaultConfig(ArcgisConfig config) {
        centerAt(config.getDefaultMapCenterLongitude(), config.getDefaultMapCenterLatitude(), config.getDefaultMapScale());
    }

    private void centerAt(double x, double y) {
        centerAt(x, y, 400);
    }

    private void centerAt(double x, double y, double scale) {
        LocationMercator coordinate = LocationCoordinateUtils.gps84ToMapMercator(x, y);
        Point point = new Point(coordinate.x, coordinate.y);
        mMapView.setViewpointCenterAsync(point, scale);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_arcgis_map, this);
        mMapView = view.findViewById(R.id.mapview);
    }

    public MapView getMap() {
        return mMapView;
    }

    public void onPause() {
        if (mMapView != null) mMapView.pause();
    }

    public void onResume() {
        if (mMapView != null) mMapView.resume();
    }

    public void onDestroy() {
        if (mMapView != null) mMapView.dispose();
    }
}
