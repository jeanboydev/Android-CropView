package com.jeanboy.cropview.cropper;

import android.app.Activity;
import android.net.Uri;

/**
 * Created by Next on 2016/8/3.
 */
public interface CropperHandler {

    Activity getActivity();

    CropperParams getParams();

    void onCropped(Uri uri);

    void onCropCancel();

    void onCropFailed(String msg);


}
