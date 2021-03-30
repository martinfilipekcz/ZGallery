package com.mzelzoghbi.zgallery.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.mzelzoghbi.zgallery.Constants;
import com.mzelzoghbi.zgallery.R;

import java.util.ArrayList;

/**
 * Created by mohamedzakaria on 8/11/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected ArrayList<String> imageURLs;
    protected int toolbarTitleColor;
    protected int toolbarColorResId;
    protected int statusBarColorResId;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // get values
        imageURLs = getIntent().getStringArrayListExtra(Constants.IntentPassingParams.IMAGES);
        toolbarColorResId = getIntent().getIntExtra(Constants.IntentPassingParams.TOOLBAR_COLOR_ID, -1);
        statusBarColorResId = getIntent().getIntExtra(Constants.IntentPassingParams.STATUS_BAR_COLOR_ID, -1);
        title = getIntent().getStringExtra(Constants.IntentPassingParams.TITLE);
        toolbarTitleColor = getIntent().getIntExtra(Constants.IntentPassingParams.TOOLBAR_TITLE_COLOR, Color.BLACK);

        if (getSupportActionBar() == null) {
            setSupportActionBar(mToolbar);
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.setTitleTextColor(toolbarTitleColor);

            if (toolbarTitleColor == Color.BLACK) {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.zg_ic_arrow_back_black);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.zg_ic_arrow_back_white);
            }

            if (toolbarColorResId != -1) {
                mToolbar.setBackgroundColor(getResources().getColor(toolbarColorResId));
            }

            if (statusBarColorResId != -1 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();

                if (window != null) {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(ContextCompat.getColor(this, statusBarColorResId));
                }
            }

            if (title != null) {
                getSupportActionBar().setTitle(title);
            }
        } else {
            mToolbar.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        afterInflation();
    }


    protected abstract int getResourceLayoutId();

    protected abstract void afterInflation();
}
