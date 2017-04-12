package com.touchtechnologies.startdagger.controller.interactor.impl;

import com.touchtechnologies.startdagger.api.service.ApiUserService;
import com.touchtechnologies.startdagger.controller.interactor.UserInteractor;
import com.touchtechnologies.startdagger.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class UserInteractorImpl implements UserInteractor {

    @Inject
    ApiUserService apiUserService;

    @Inject
    public UserInteractorImpl(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    @Override
    public Observable<Response<List<User>>> getAll() {
        return apiUserService.getUsers();
    }

    @Override
    public Observable<Response<User>> getById(Integer id) {
        return apiUserService.getUser(String.valueOf(id));
    }

    @Override
    public Observable<Response<User>> add(User value) {
        return apiUserService.addUser(value);
    }
}
