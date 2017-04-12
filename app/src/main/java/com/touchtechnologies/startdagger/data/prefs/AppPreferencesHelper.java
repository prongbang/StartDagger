package com.touchtechnologies.startdagger.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dev-touch on 4/3/2017.
 */

public class AppPreferencesHelper {

    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NEW_JOB_RETRY_COUNT = "new_post_retry_count";

    private final SharedPreferences mSharedPreferences;

    public AppPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences("app_cfg", Context.MODE_PRIVATE);
    }

    public long getUserId() {
        return mSharedPreferences.getLong(KEY_USER_ID, 1);
    }

    public void setUserId(long userId) {
        mSharedPreferences.edit().putLong(KEY_USER_ID, userId).apply();
    }

    public int getNewPostRetryCount() {
        return mSharedPreferences.getInt(KEY_NEW_JOB_RETRY_COUNT, 20);
    }

    public void setNewPostRetryCount(int count) {
        mSharedPreferences.edit().putInt(KEY_NEW_JOB_RETRY_COUNT, count).apply();
    }

}
