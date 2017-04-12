package com.touchtechnologies.startdagger.controller.presenter.impl;

import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.controller.interactor.FeedInteractor;
import com.touchtechnologies.startdagger.controller.presenter.FeedPresenter;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.model.vo.FeedItem;
import com.touchtechnologies.startdagger.utils.HttpUtil;
import com.touchtechnologies.startdagger.views.FeedView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public class FeedPresenterImpl implements FeedPresenter {

    private FeedView view;

    @Inject
    FeedInteractor feedInteractor;

    @Inject
    public FeedPresenterImpl(FeedInteractor feedInteractor) {
        this.feedInteractor = feedInteractor;
    }

    @Override
    public void bind(FeedView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void onNextResults(Response<List<FeedItem>> response) {

    }

    @Override
    public void onNextResult(Response<FeedItem> response) {
        if (response != null) {
            switch (response.code()) {
                case HttpUtil.HTTP_STATUS_OK:
                    if (view != null) view.onFeedSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_CREATED:
                    if (view != null) view.onFeedSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_UNAUTHORIZED:
                    if (view != null)
                        view.onUnauthorized(view.getString(R.string.label_unauthorized));
                    break;
                default:
                    sendErrorMessage(response);
                    break;
            }
        } else {
            if (view != null) view.onFeedError(null);
        }
    }

    @Override
    public void onErrorResult(Throwable t) {
        sendErrorMessage(t);
    }

    @Override
    public void getAll() {

    }

    @Override
    public void getById(Integer id) {

    }

    @Override
    public void add(FeedItem value) {

    }

    @Override
    public void getFeedByUserId(Integer id) {
        feedInteractor.getByUserId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<FeedItem>>() {
                    @Override
                    public void onNext(Response<FeedItem> value) {
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
        if (view != null) view.onFeedError(errorMessage);
    }

    private void sendErrorMessage(Throwable t) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(t.getMessage());
        errorMessage.setThrowable(t);
        if (view != null) view.onFeedError(errorMessage);
    }
}
