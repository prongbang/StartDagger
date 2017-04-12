package com.touchtechnologies.startdagger.views.base;

import android.support.annotation.StringRes;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    boolean isNetworkConnected();

    void hideKeyboard();

}
