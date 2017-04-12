package com.touchtechnologies.startdagger.api.service;

import com.touchtechnologies.startdagger.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface ApiPostService {

    @GET("post")
    Observable<Response<List<Post>>> getPosts();

    @GET("post/{postId}")
    Observable<Response<Post>> getPost(@Path("postId") String postId);

    @POST("post")
    Observable<Response<Post>> addPost(@Body Post post);

    @GET("post/user/{userId}")
    Observable<Response<List<Post>>> getPostByUserId(@Path("userId") String userId);

}
