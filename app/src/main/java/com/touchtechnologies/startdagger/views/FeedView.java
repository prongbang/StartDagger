package com.touchtechnologies.startdagger.views;

import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.model.vo.FeedItem;

import java.util.List;

/**
 * Created by dev-touch on 4/11/2017.
 */

public interface FeedView extends BaseView {

    void getFeed();

    void onFeedError(ErrorMessage errorMessage);

    void onFeedSuccess(FeedItem data);

    void onFeedSuccess(List<FeedItem> listData);

}
