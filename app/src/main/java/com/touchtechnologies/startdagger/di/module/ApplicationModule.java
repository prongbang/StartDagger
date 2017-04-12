package com.touchtechnologies.startdagger.di.module;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import com.touchtechnologies.startdagger.MainApplication;
import com.touchtechnologies.startdagger.api.service.ApiPostService;
import com.touchtechnologies.startdagger.api.service.ApiUserService;
import com.touchtechnologies.startdagger.controller.interactor.FeedInteractor;
import com.touchtechnologies.startdagger.controller.interactor.PostInteractor;
import com.touchtechnologies.startdagger.controller.interactor.UserInteractor;
import com.touchtechnologies.startdagger.controller.interactor.impl.FeedInteractorImpl;
import com.touchtechnologies.startdagger.controller.interactor.impl.PostInteractorImpl;
import com.touchtechnologies.startdagger.controller.interactor.impl.UserInteractorImpl;
import com.touchtechnologies.startdagger.controller.presenter.FeedPresenter;
import com.touchtechnologies.startdagger.controller.presenter.PostPresenter;
import com.touchtechnologies.startdagger.controller.presenter.UserPresenter;
import com.touchtechnologies.startdagger.controller.presenter.impl.FeedPresenterImpl;
import com.touchtechnologies.startdagger.controller.presenter.impl.PostPresenterImpl;
import com.touchtechnologies.startdagger.controller.presenter.impl.UserPresenterImpl;
import com.touchtechnologies.startdagger.data.db.storage.AccessTokenStorage;
import com.touchtechnologies.startdagger.data.db.storage.UserStorage;
import com.touchtechnologies.startdagger.data.prefs.AppPreferencesHelper;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.views.adapter.PostAdapter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dev-touch on 4/10/2017.
 */

@Module
public class ApplicationModule {

    private final MainApplication mainApplication;

    public ApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mainApplication;
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    public AppPreferencesHelper appPreferencesHelper() {
        return new AppPreferencesHelper(mainApplication);
    }

    @Provides
    @Singleton
    public NotificationManagerCompat notificationCompat() {
        return NotificationManagerCompat.from(mainApplication);
    }

    @Provides
    @Singleton
    public AccessTokenStorage accessTokenStorage() {
        return new AccessTokenStorage();
    }

    @Provides
    @Singleton
    public UserStorage userStorage() {
        return new UserStorage();
    }

    @Provides
    @Singleton
    public UserInteractor userInteractor(ApiUserService apiUserService) {
        return new UserInteractorImpl(apiUserService);
    }

    @Provides
    @Singleton
    public PostInteractor postInteractor(ApiPostService apiPostService) {
        return new PostInteractorImpl(apiPostService);
    }

    @Provides
    @Singleton
    public FeedInteractor feedInteractor(ApiUserService apiUserService, ApiPostService apiPostService) {
        return new FeedInteractorImpl(apiUserService, apiPostService);
    }

    @Provides
    public PostPresenter postPresenter(PostInteractor postInteractor) {
        return new PostPresenterImpl(postInteractor);
    }

    @Provides
    public UserPresenter userPresenter(UserInteractor userInteractor) {
        return new UserPresenterImpl(userInteractor);
    }

    @Provides
    public FeedPresenter feedPresenter(FeedInteractor feedInteractor) {
        return new FeedPresenterImpl(feedInteractor);
    }

    @Provides
    public ErrorMessage errorMessage() {
        return new ErrorMessage();
    }

    @Provides
    public PostAdapter postAdapter(Context context) {
        return new PostAdapter(context);
    }

}
