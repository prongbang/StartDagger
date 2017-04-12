package com.touchtechnologies.startdagger.controller.interactor.impl;

import com.touchtechnologies.startdagger.api.service.ApiPostService;
import com.touchtechnologies.startdagger.controller.interactor.PostInteractor;
import com.touchtechnologies.startdagger.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public class PostInteractorImpl implements PostInteractor {

    @Inject
    ApiPostService apiPostService;

    @Inject
    public PostInteractorImpl(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }

    @Override
    public Observable<Response<List<Post>>> getAll() {
        return apiPostService.getPosts();
    }

    @Override
    public Observable<Response<Post>> getById(Integer id) {
        return apiPostService.getPost(String.valueOf(id));
    }

    @Override
    public Observable<Response<Post>> add(Post value) {
        return apiPostService.addPost(value);
    }

    @Override
    public Observable<Response<List<Post>>> getByUserId(String id) {
        return apiPostService.getPostByUserId(id);
    }
}
