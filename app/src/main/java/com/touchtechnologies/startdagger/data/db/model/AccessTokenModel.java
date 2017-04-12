package com.touchtechnologies.startdagger.data.db.model;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by dev-touch on 4/10/2017.
 */

@RealmClass
public class AccessTokenModel implements RealmModel {

    @PrimaryKey
    private String userId;

    private String access_token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
