package com.touchtechnologies.startdagger.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.provider.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class CommonUtil {

    private static final String TAG = "CommonUtil";

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }


}
