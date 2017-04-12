package com.touchtechnologies.startdagger.controller.presenter.impl;

import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.controller.interactor.PostInteractor;
import com.touchtechnologies.startdagger.controller.presenter.PostPresenter;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.utils.HttpUtil;
import com.touchtechnologies.startdagger.views.PostView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public class PostPresenterImpl implements PostPresenter {

    private PostView view;

    @Inject
    PostInteractor postInteractor;

    @Inject
    public PostPresenterImpl(PostInteractor postInteractor) {
        this.postInteractor = postInteractor;
    }

    @Override
    public void bind(PostView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void onNextResults(Response<List<Post>> response) {
        if (response != null) {
            switch (response.code()) {
                case HttpUtil.HTTP_STATUS_OK:
                    if (view != null) view.onPostSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_CREATED:
                    if (view != null) view.onPostSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_UNAUTHORIZED:
                    if (view != null) view.onUnauthorized(view.getString(R.string.label_unauthorized));
                    break;
                default:
                    sendErrorMessage(response);
                    break;
            }
        } else {
            if (view != null) view.onPostError(null);
        }
    }

    @Override
    public void onNextResult(Response<Post> response) {
        if (response != null) {
            switch (response.code()) {
                case HttpUtil.HTTP_STATUS_OK:
                    if (view != null) view.onPostSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_CREATED:
                    if (view != null) view.onPostSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_UNAUTHORIZED:
                    if (view != null) view.onUnauthorized(view.getString(R.string.label_unauthorized));
                    break;
                default:
                    sendErrorMessage(response);
                    break;
            }
        } else {
            if (view != null) view.onPostError(null);
        }
    }

    @Override
    public void onErrorResult(Throwable t) {
        sendErrorMessage(t);
    }

    @Override
    public void getAll() {
        postInteractor.getAll().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<List<Post>>>() {
                    @Override
                    public void onNext(Response<List<Post>> value) {
                        onNextResults(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onErrorResult(e);
                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });
    }

    @Override
    public void getById(Integer id) {
        postInteractor.getById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<Post>>() {
                    @Override
                    public void onNext(Response<Post> value) {
                        onNextResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onErrorResult(e);
                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });
    }

    @Override
    public void add(Post value) {
        postInteractor.add(value)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<Post>>() {
                    @Override
                    public void onNext(Response<Post> value) {
                        onNextResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onErrorResult(e);
                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });
    }

    private void sendErrorMessage(Response<?> response) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(response.code());
        errorMessage.setMessage(response.message());
        if (view != null) view.onPostError(errorMessage);
    }

    private void sendErrorMessage(Throwable t) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(t.getMessage());
        errorMessage.setThrowable(t);
        if (view != null) view.onPostError(errorMessage);
    }
}
