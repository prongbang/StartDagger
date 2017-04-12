package com.touchtechnologies.startdagger.api.service;

import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dev-touch on 4/3/2017.
 */

public interface ApiUserService {

    @GET("user")
    Observable<Response<List<User>>> getUsers();

    @GET("user/{userId}")
    Observable<Response<User>> getUser(@Path("userId") String userId);

    @POST("user")
    Observable<Response<User>> addUser(@Body User user);

}
