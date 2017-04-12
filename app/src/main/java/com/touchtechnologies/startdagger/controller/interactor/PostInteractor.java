package com.touchtechnologies.startdagger.controller.interactor;

import com.touchtechnologies.startdagger.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public interface PostInteractor extends BaseInteractor<Post, Integer> {

    Observable<Response<List<Post>>> getByUserId(String id);

}
