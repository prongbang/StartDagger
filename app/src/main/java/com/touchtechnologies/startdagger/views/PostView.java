package com.touchtechnologies.startdagger.views;

import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;

import java.util.List;

/**
 * Created by dev-touch on 4/11/2017.
 */

public interface PostView extends BaseView {

    void onPostError(ErrorMessage errorMessage);

    void onPostSuccess(Post data);

    void onPostSuccess(List<Post> listData);

}
