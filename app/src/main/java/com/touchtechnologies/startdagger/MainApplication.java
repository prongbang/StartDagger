package com.touchtechnologies.startdagger;

import android.app.Application;

import com.touchtechnologies.startdagger.data.db.RealmConfig;
import com.touchtechnologies.startdagger.di.component.AppComponent;
import com.touchtechnologies.startdagger.di.component.DaggerAppComponent;
import com.touchtechnologies.startdagger.di.module.ApplicationModule;

import dagger.Module;
import io.realm.Realm;

/**
 * Created by dev-touch on 4/3/2017.
 */

@Module
public class MainApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Realm.setDefaultConfiguration(RealmConfig.init());

        mAppComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
