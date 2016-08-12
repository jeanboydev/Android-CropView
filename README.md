##CropView

------

##介绍

android image cropping library. Support from camera, gallery, image rotate.

Android的图像裁剪库。支持从相机，图库选择图片，裁切时图像旋转。

##使用

* 导入lib-cropview并在AndroidManifest.xml中添加Activity
```java 
  <activity android:name="com.jeanboy.cropview.cropper.CropActivity"
            android:theme="@style/AppTheme.NoActionBar"/>
```

* 实现CropperHandler并实现方法
```java
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public CropperParams getParams() {
		//配置裁切框比例
        return new CropperParams(1, 1);
    }

    @Override
    public void onCropped(Uri uri) {
        Log.d("=====onCropped======", "======裁切成功=======" + uri);
    }

    @Override
    public void onCropCancel() {
        Log.d("=====onCropCancel====", "======裁切取消=====");
    }

    @Override
    public void onCropFailed(String msg) {
        Log.d("=====onCropFailed===", "=======裁切失败======" + msg);
    }
```

* 初始化CropperManager

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CropperManager.getInstance().build(this);
    }


 	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropperManager.getInstance().handlerResult(requestCode, resultCode, data);
    }

```

* 调用操作
```java
  CropperManager.getInstance().pickFromCamera();//拍照裁切
  CropperManager.getInstance().pickFromGallery();//图库选择裁切
```
## 注意事项
CropActivity不需要ActionBar
```java
  <style name="AppTheme.NoActionBar" parent="AppTheme">
        <!-- 关闭ActionBar -->
        <item name="windowActionBar">false</item>
        <!-- 隐藏title -->
        <item name="windowNoTitle">true</item>
	</style>
```


## Demo

![演示][1]

## 感谢

* [IsseiAoki/SimpleCropView](https://github.com/IsseiAoki/SimpleCropView)

## 关于我

* Mail: jeanboy@foxmail.com

## License

    Copyright 2015 jeanboy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

  [1]: https://github.com/freekite/CropView/blob/master/resource/ScreenRecord.gif
