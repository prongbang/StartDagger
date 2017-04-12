package com.touchtechnologies.startdagger.controller.interactor;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface BaseInteractor<M, PK> {

    Observable<Response<List<M>>> getAll();

    Observable<Response<M>> getById(PK id);

    Observable<Response<M>> add(M value);

}
