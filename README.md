## CropView

![](https://img.shields.io/badge/platform-Android-brightgreen.svg) ![](https://img.shields.io/badge/language-java-yellow.svg) ![](https://img.shields.io/badge/license-Apache--2.0-blue.svg)

------



## 介绍

android image cropping library. Support from camera, gallery, image rotate.

Android的图像裁剪库。支持从相机，图库选择图片，裁切时图像旋转。

## 使用

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
        //不约束裁切比例
        // return new CropperParams(0, 0);
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

如果对你有帮助，请 star 一下，然后 follow 我，给我增加一下分享动力，谢谢！

如果你有什么疑问或者问题，可以提交 issue 和 request，发邮件给我 jeanboy@foxmail.com 。

或者加入下面的 QQ 群来一起学习交流。

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=bbbd62c0860ce7c1a6119030f51df102bb0d3ecc12cf66b4d8887941233c6e78"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="Android技术进阶：386463747" title="Android技术进阶：386463747"></a>

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
