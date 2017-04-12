package com.touchtechnologies.startdagger.controller.presenter;

import java.util.List;

import retrofit2.Response;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface BasePresenter<V, M, PK> {

    void bind(V view);

    void unbind();

    void onNextResults(Response<List<M>> response);

    void onNextResult(Response<M> response);

    void onErrorResult(Throwable t);

    void getAll();

    void getById(PK id);

    void add(M value);

}
