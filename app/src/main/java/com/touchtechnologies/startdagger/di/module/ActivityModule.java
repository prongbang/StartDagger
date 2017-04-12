package com.touchtechnologies.startdagger.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dev-touch on 4/10/2017.
 */

@Module
public class ActivityModule {

    final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Context activityContext() {
        return mActivity;
    }

}
