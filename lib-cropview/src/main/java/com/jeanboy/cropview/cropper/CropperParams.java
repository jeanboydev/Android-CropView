package com.jeanboy.cropview.cropper;

/**
 * Created by Next on 2016/8/3.
 */
public class CropperParams {

    public CropperParams(int aspectX, int aspectY) {
        this.aspectX = aspectX;
        this.aspectY = aspectY;
    }

    public static final String PICK_URI = "pick_uri";
    public static final String ASPECT_X = "aspectX";
    public static final String ASPECT_Y = "aspectY";
    public static final int REQUEST_PICK_CAMERA = 10010;
    public static final int REQUEST_PICK_IMAGE = 10011;
    public static final int REQUEST_KITKAT_PICK_IMAGE = 10012;
    public static final int REQUEST_CROPPED = 10013;

    public static final int DEFAULT_ASPECT = 1;

    public int aspectX = DEFAULT_ASPECT;
    public int aspectY = DEFAULT_ASPECT;


}
