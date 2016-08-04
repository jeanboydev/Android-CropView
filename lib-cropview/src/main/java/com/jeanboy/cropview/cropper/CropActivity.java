package com.jeanboy.cropview.cropper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jeanboy.cropview.CropImageView;
import com.jeanboy.cropview.R;
import com.jeanboy.cropview.callback.CropCallback;
import com.jeanboy.cropview.callback.LoadCallback;
import com.jeanboy.cropview.callback.SaveCallback;

import java.io.File;

public class CropActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private CropImageView cropImageView;

    private LoadingDialog loadingDialog;

    private Uri imgUri;

    private int aspect_x, aspect_Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        setupToolbar();
        setupView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("截取图片");
            }
        }
    }

    public void setupView() {
        aspect_x = getIntent().getExtras().getInt(CropperParams.ASPECT_X, CropperParams.DEFAULT_ASPECT);
        aspect_Y = getIntent().getExtras().getInt(CropperParams.ASPECT_Y, CropperParams.DEFAULT_ASPECT);
        imgUri = getIntent().getExtras().getParcelable(CropperParams.PICK_URI);

        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        loadingDialog = new LoadingDialog(this);
        cropImageView.setCustomRatio(aspect_x, aspect_Y);
        loadingDialog.show();
        cropImageView.startLoad(imgUri, mLoadCallback);
    }

    public void cropImage() {//裁切图片
        loadingDialog.show();
        cropImageView.startCrop(createSaveUri(), mCropCallback, mSaveCallback);
    }

    public void rotateLeft(View v) {//向左旋转
        cropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
    }

    public void rotateRight(View v) {//向右旋转
        cropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
    }

    public void done(View v) {//完成按钮
        cropImage();
    }

    public Uri createSaveUri() {//创建裁切完文件保存目录，默认保存在/mnt/sdcard/Android/data/<包名>/cache
        return Uri.fromFile(new File(getExternalCacheDir().getAbsolutePath(), "cropped_" + System.currentTimeMillis() + ".jpg"));
    }

    // Callbacks ///////////////////////////////////////////////////////////////////////////////////

    private final LoadCallback mLoadCallback = new LoadCallback() {
        @Override
        public void onSuccess() {
            loadingDialog.dismiss();
        }

        @Override
        public void onError() {
            loadingDialog.dismiss();
        }
    };

    private final CropCallback mCropCallback = new CropCallback() {
        @Override
        public void onSuccess(Bitmap cropped) {
        }

        @Override
        public void onError() {
        }
    };

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {
            loadingDialog.dismiss();
            Log.d("================", "====" + outputUri);
            setResult(RESULT_OK, new Intent().putExtra(CropperParams.PICK_URI, outputUri));
            finish();
        }

        @Override
        public void onError() {
            loadingDialog.dismiss();
        }
    };
}
