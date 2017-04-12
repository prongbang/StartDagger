package com.touchtechnologies.startdagger.views.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.GsonBuilder;
import com.touchtechnologies.startdagger.MainApplication;
import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.controller.presenter.FeedPresenter;
import com.touchtechnologies.startdagger.controller.presenter.PostPresenter;
import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.vo.ErrorMessage;
import com.touchtechnologies.startdagger.model.vo.FeedItem;
import com.touchtechnologies.startdagger.utils.LogUtil;
import com.touchtechnologies.startdagger.views.FeedView;
import com.touchtechnologies.startdagger.views.PostView;
import com.touchtechnologies.startdagger.views.adapter.PostAdapter;
import com.touchtechnologies.startdagger.views.base.BaseActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements FeedView, PostView, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    FeedPresenter feedPresenter;

    @Inject
    PostPresenter postPresenter;

    @Inject
    PostAdapter postAdapter;

    @BindView(R.id.et_message)
    EditText etMessage;

    @BindView(R.id.btn_send)
    Button btnSend;

    @BindView(R.id.list_post)
    ListView listPost;

    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject activity
        getComponent().inject(this);

        ButterKnife.bind(this);

        btnSend.setOnClickListener(this);

        getFeed();

        // bind view and presenter
        feedPresenter.bind(this);
        postPresenter.bind(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        feedPresenter.unbind();
        postPresenter.unbind();
    }

    @Override
    public void getFeed() {
        showLoading();
        feedPresenter.getFeedByUserId(1);
    }

    @Override
    public void onFeedError(ErrorMessage errorMessage) {
        hideLoading();
        LogUtil.e(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(errorMessage));
    }

    @Override
    public void onFeedSuccess(List<FeedItem> listData) {

    }

    @Override
    public void onUnauthorized(String message) {
        hideLoading();
        //TODO go to login page
    }


    @Override
    public void onFeedSuccess(FeedItem data) {
        hideLoading();
        if (data != null) {
            posts = data.getPosts();
            listPost.setAdapter(postAdapter);
            postAdapter.setPosts(posts);
        }
        LogUtil.i(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(data));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if (!StringUtils.isBlank(etMessage.getText())) {
                    showLoading();
                    int userId = 1;
                    Post post = new Post();
                    post.setClient_id(getSessionId());
                    post.setId(posts.size()+1);
                    post.setUser_id(userId);
                    post.setText(String.valueOf(etMessage.getText()));
                    postPresenter.add(post);
                }
                break;
        }
    }

    @Override
    public void onPostError(ErrorMessage errorMessage) {
        hideLoading();
        LogUtil.e(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(errorMessage));
    }

    @Override
    public void onPostSuccess(Post data) {
        hideLoading();
        if (data != null) {
            posts.add(data);
            postAdapter.setPosts(posts);
        }
        etMessage.setText("");
    }

    @Override
    public void onPostSuccess(List<Post> listData) {

    }
}
