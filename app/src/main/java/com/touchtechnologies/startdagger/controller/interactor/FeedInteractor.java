package com.touchtechnologies.startdagger.controller.interactor;

import com.touchtechnologies.startdagger.controller.presenter.BasePresenter;
import com.touchtechnologies.startdagger.model.vo.FeedItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by dev-touch on 4/11/2017.
 */

public interface FeedInteractor {

    Observable<Response<FeedItem>> getByUserId(Integer id);

    Observable<Response<List<FeedItem>>> getAll();

}
