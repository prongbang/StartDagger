package com.touchtechnologies.startdagger.views;

import android.support.annotation.StringRes;

import com.touchtechnologies.startdagger.model.vo.ErrorMessage;

import java.util.List;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface BaseView<M> {

    String getString(@StringRes int idString);

    void onUnauthorized(String message);

}
