package com.touchtechnologies.startdagger.controller.interactor.impl;

import com.touchtechnologies.startdagger.api.service.ApiPostService;
import com.touchtechnologies.startdagger.api.service.ApiUserService;
import com.touchtechnologies.startdagger.controller.interactor.FeedInteractor;
import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.User;
import com.touchtechnologies.startdagger.model.vo.FeedItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public class FeedInteractorImpl implements FeedInteractor {

    @Inject
    ApiUserService apiUserService;

    @Inject
    ApiPostService apiPostService;

    @Inject
    public FeedInteractorImpl(ApiUserService apiUserService, ApiPostService apiPostService) {
        this.apiUserService = apiUserService;
        this.apiPostService = apiPostService;
    }

    @Override
    public Observable<Response<FeedItem>> getByUserId(Integer id) {

        Observable<Response<User>> userObservable = apiUserService.getUser(String.valueOf(id));
        Observable<Response<List<Post>>> postObservable = apiPostService.getPostByUserId(String.valueOf(id));
        Observable<Response<FeedItem>> feedItemObservable = Observable.zip(userObservable, postObservable, new BiFunction<Response<User>, Response<List<Post>>, Response<FeedItem>>() {
            @Override
            public Response<FeedItem> apply(Response<User> userResponse, Response<List<Post>> listResponse) throws Exception {

                return Response.success(
                        new FeedItem(userResponse.body(), listResponse.body()),
                        userResponse.headers()
                );
            }
        });

        return feedItemObservable;
    }

    @Override
    public Observable<Response<List<FeedItem>>> getAll() {
        return null;
    }
}
