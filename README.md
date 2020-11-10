# ArcgisHelper
An Android library that makes developers use Arcgis 100 offline tiles extremely easy.

## Quick Setup
#### 1.Download library
#### 2.Edit your **build.gradle** file and add below dependency.

``` groovy
dependencies {
    implementation project (':lib_arcgis')
}
```

#### 3.New network security config file to avoid errors for https connected.

``` groovy
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

#### 4.Add networkSecurityConfig in your Androidmanifest.xml

``` groovy
android:networkSecurityConfig="@xml/network_security_config"
```

#### 5.Add uses permission in your Androidmanifest.xml

That's OK, ArcgisHelper can work with that.

After setup, you can experience the powerful functions.

---

#### 1.Add ArcgisMapView in your view field.
```
        <com.arcgishelper.lib_arcgis.ArcgisMapView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

#### 2.Create ArcgisConfig and setup when your mapview created.

```
        ArcgisConfig config = new ArcgisConfig();
        mMapview.setConfig(config).buildMap();
```

## IMPORTANT
IN VERSION 11.10 
LIBRARY ONLY SUPPORT OFFLINE TILES FOR GOOGLE, AMAP, TENCENT, BAIDU, OPENCYCLE

**developer can use arcgis api in your module**
    

## License
```
Copyright (C)  Ronnyxie, ArcgisHelper Framework Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
