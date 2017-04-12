package com.touchtechnologies.startdagger.controller.presenter.impl;

import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.controller.interactor.UserInteractor;
import com.touchtechnologies.startdagger.controller.presenter.UserPresenter;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.model.User;
import com.touchtechnologies.startdagger.utils.HttpUtil;
import com.touchtechnologies.startdagger.views.UserView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class UserPresenterImpl implements UserPresenter {

    private UserView view;

    @Inject
    UserInteractor userInteractor;

    @Inject
    public UserPresenterImpl(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    @Override
    public void bind(UserView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void onNextResults(Response<List<User>> response) {
        if (response != null) {
            switch (response.code()) {
                case HttpUtil.HTTP_STATUS_OK:
                    if (view != null) view.onUserSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_CREATED:
                    if (view != null) view.onUserSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_UNAUTHORIZED:
                    if (view != null) view.onUnauthorized(view.getString(R.string.label_unauthorized));
                    break;
                default:
                    sendErrorMessage(response);
                    break;
            }
        } else {
            if (view != null) view.onUserError(null);
        }
    }

    @Override
    public void onNextResult(Response<User> response) {
        if (response != null) {
            switch (response.code()) {
                case HttpUtil.HTTP_STATUS_OK:
                    if (view != null) view.onUserSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_CREATED:
                    if (view != null) view.onUserSuccess(response.body());
                    break;
                case HttpUtil.HTTP_STATUS_UNAUTHORIZED:
                    if (view != null) view.onUnauthorized(view.getString(R.string.label_unauthorized));
                    break;
                default:
                    sendErrorMessage(response);
                    break;
            }
        } else {
            if (view != null) view.onUserError(null);
        }
    }

    @Override
    public void onErrorResult(Throwable t) {
        sendErrorMessage(t);
    }

    @Override
    public void getAll() {
        userInteractor.getAll().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<List<User>>>() {
                    @Override
                    public void onNext(Response<List<User>> value) {
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
        userInteractor.getById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<User>>() {
                    @Override
                    public void onNext(Response<User> value) {
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
    public void add(User value) {
        userInteractor.add(value)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<User>>() {
                    @Override
                    public void onNext(Response<User> value) {
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
        if (view != null) view.onUserError(errorMessage);
    }

    private void sendErrorMessage(Throwable t) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(t.getMessage());
        errorMessage.setThrowable(t);
        if (view != null) view.onUserError(errorMessage);
    }
}
