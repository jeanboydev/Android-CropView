package com.jeanboy.cropper;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jeanboy.cropview.cropper.CropperHandler;
import com.jeanboy.cropview.cropper.CropperManager;
import com.jeanboy.cropview.cropper.CropperParams;

import java.io.IOException;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements CropperHandler {


    private ImageView iv_cropped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_cropped = (ImageView) findViewById(R.id.iv_cropped);

        CropperManager.getInstance().build(this);
    }

    public void fromCamera(View v) {
        MainActivityPermissionsDispatcher.pickFromCameraWithCheck(this);
    }

    public void fromGallery(View v) {
        MainActivityPermissionsDispatcher.pickFromGalleryWithCheck(this);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public CropperParams getParams() {
        return new CropperParams(1, 1);
    }

    @Override
    public void onCropped(Uri uri) {
        Log.d("=====onCropped======", "======裁切成功=======" + uri);
        try {
            Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            iv_cropped.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCropCancel() {
        Log.d("=====onCropCancel====", "======裁切取消=====");
    }

    @Override
    public void onCropFailed(String msg) {
        Log.d("=====onCropFailed===", "=======裁切失败======" + msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropperManager.getInstance().handlerResult(requestCode, resultCode, data);
    }


    /*-------------------权限处理-----------------*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void pickFromCamera() {
        CropperManager.getInstance().pickFromCamera();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void pickFromGallery() {
        CropperManager.getInstance().pickFromGallery();
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog("该操作需要使用摄像头，请允许！", request);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void showRationaleForPick(PermissionRequest request) {//权限说明
        showRationaleDialog("该操作需要操作内存卡，请允许！", request);
    }


    private void showRationaleDialog(String msg, final PermissionRequest request) {
        new AlertDialog.Builder(getActivity())
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(msg)
                .show();
    }
}
