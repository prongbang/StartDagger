package com.touchtechnologies.startdagger.model.vo;

import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.User;

import java.util.List;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class FeedItem {

    private int id;
    private User user;
    private List<Post> posts;

    public FeedItem(User user, List<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
