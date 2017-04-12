package com.touchtechnologies.startdagger.di.component;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import com.touchtechnologies.startdagger.api.module.ApiModule;
import com.touchtechnologies.startdagger.api.service.ApiPostService;
import com.touchtechnologies.startdagger.api.service.ApiUserService;
import com.touchtechnologies.startdagger.controller.interactor.FeedInteractor;
import com.touchtechnologies.startdagger.controller.interactor.PostInteractor;
import com.touchtechnologies.startdagger.controller.interactor.UserInteractor;
import com.touchtechnologies.startdagger.controller.presenter.FeedPresenter;
import com.touchtechnologies.startdagger.controller.presenter.PostPresenter;
import com.touchtechnologies.startdagger.controller.presenter.UserPresenter;
import com.touchtechnologies.startdagger.data.db.storage.AccessTokenStorage;
import com.touchtechnologies.startdagger.data.db.storage.UserStorage;
import com.touchtechnologies.startdagger.data.prefs.AppPreferencesHelper;
import com.touchtechnologies.startdagger.di.module.ApplicationModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dev-touch on 4/3/2017.
 * ถ้าจะ Inject ใน Activity ให้มาประกาศ method ในนี้ด้วย
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {

    EventBus eventBus();

    ApiPostService apiPostService();

    ApiUserService apiUserService();

    Context appContext();

    AppPreferencesHelper appPreferencesHelper();

    NotificationManagerCompat notificationManagerCompat();

    AccessTokenStorage accessTokenStorage();

    UserStorage userStorage();

    PostPresenter postPresenter();

    UserPresenter userPresenter();

    FeedPresenter feedPresenter();

}
