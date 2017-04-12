package com.touchtechnologies.startdagger.views;

import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.User;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;

import java.util.List;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface UserView extends BaseView {

    void onUserError(ErrorMessage errorMessage);

    void onUserSuccess(User data);

    void onUserSuccess(List<User> listData);

}
