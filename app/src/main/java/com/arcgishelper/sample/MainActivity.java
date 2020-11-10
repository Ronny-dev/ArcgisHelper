package com.arcgishelper.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arcgishelper.lib_arcgis.ArcgisMapView;
import com.arcgishelper.lib_arcgis.entity.ArcgisConfig;

public class MainActivity extends AppCompatActivity {

    private ArcgisMapView mMapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
    }

    private void initMap() {
        mMapview = findViewById(R.id.map);
        ArcgisConfig config = new ArcgisConfig();
        mMapview.setConfig(config).buildMap();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}