package com.test.baserefreshview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xycode.xylibrary.base.PhotoSelectBaseActivity;
import com.xycode.xylibrary.utils.TS;
import com.yalantis.ucrop.UCrop;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoSelectActivity extends PhotoSelectBaseActivity {

    @Bind(R.id.btnCamera)
    Button btnCamera;
    @Bind(R.id.btnAlbum)
    Button btnAlbum;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.rlMain)
    RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_select);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnCamera, R.id.btnAlbum, R.id.btnCancel, R.id.rlMain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:
                onCamera();
                break;
            case R.id.btnAlbum:
                onAlbum();
                break;
            case R.id.btnCancel:
            case R.id.rlMain:
                finish();
                break;
        }
    }

    @Override
    protected void permissionOnDenied(String permission) {
        TS.show("no " + permission);
    }

    @Override
    protected UCrop.Options getCropOptions() {
        UCrop.Options options = new UCrop.Options();
        options.setFreeStyleCropEnabled(true);
        options.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        return options;
    }
}
