package com.touchtechnologies.startdagger.controller.presenter;

import com.touchtechnologies.startdagger.model.vo.FeedItem;
import com.touchtechnologies.startdagger.views.FeedView;

/**
 * Created by dev-touch on 4/11/2017.
 */

public interface FeedPresenter extends BasePresenter<FeedView, FeedItem, Integer> {

    void getFeedByUserId(Integer id);

}
