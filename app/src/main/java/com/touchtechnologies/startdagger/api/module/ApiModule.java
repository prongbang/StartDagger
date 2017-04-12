package com.touchtechnologies.startdagger.api.module;

import android.content.Context;

import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.api.service.ServiceGenerator;
import com.touchtechnologies.startdagger.api.service.ApiPostService;
import com.touchtechnologies.startdagger.api.service.ApiUserService;
import com.touchtechnologies.startdagger.data.db.storage.AccessTokenStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dev-touch on 4/3/2017.
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiUserService apiUserService(Context context, AccessTokenStorage accessTokenStorage) {

        String url = context.getString(R.string.user_service_url);

        return ServiceGenerator.createService(context, url, accessTokenStorage, ApiUserService.class);
    }

    @Singleton
    @Provides
    public ApiPostService apiPostService(Context context, AccessTokenStorage accessTokenStorage) {

        String url = context.getString(R.string.post_service_url);

        return ServiceGenerator.createService(context, url, accessTokenStorage, ApiPostService.class);
    }

}
