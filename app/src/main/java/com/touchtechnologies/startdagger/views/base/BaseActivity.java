package com.touchtechnologies.startdagger.views.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.touchtechnologies.startdagger.MainApplication;
import com.touchtechnologies.startdagger.di.component.ActivityComponent;
import com.touchtechnologies.startdagger.di.component.DaggerActivityComponent;
import com.touchtechnologies.startdagger.utils.KeyboardUtil;
import com.touchtechnologies.startdagger.views.dialogs.DialogUtil;
import com.touchtechnologies.startdagger.views.utils.LifecycleListener;
import com.touchtechnologies.startdagger.views.utils.LifecycleProvider;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseView, LifecycleProvider {

    private ActivityComponent mComponent;

    private ProgressDialog mProgressDialog;

    private String mSessionId;

    private final CopyOnWriteArrayList<LifecycleListener> mLifecycleListeners = new CopyOnWriteArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder().appComponent(getApp().getAppComponent()).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSessionId = UUID.randomUUID().toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (LifecycleListener callback : mLifecycleListeners) {
            callback.onProviderStopped();
        }
        mLifecycleListeners.clear();
    }

    public String getSessionId() {
        return mSessionId;
    }

    protected MainApplication getApp() {
        return (MainApplication) getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = DialogUtil.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtil.hideSoftInput(this);
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.add(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.remove(listener);
    }
}
