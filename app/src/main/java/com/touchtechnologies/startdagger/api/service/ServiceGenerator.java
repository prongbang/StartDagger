package com.touchtechnologies.startdagger.api.service;

import android.content.Context;

import com.touchtechnologies.startdagger.data.db.model.AccessTokenModel;
import com.touchtechnologies.startdagger.data.db.storage.AccessTokenStorage;
import com.touchtechnologies.startdagger.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dev-touch on 2/8/2017.
 */

public class ServiceGenerator {

    private static final String TAG = ServiceGenerator.class.getSimpleName();

    /**
     * Create Service Request to Server
     *
     * @param context
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createService(Context context, String url, final AccessTokenStorage accessTokenStorage, Class<T> clazz) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                try {
                    LogUtil.i(TAG, "URL ---> " + original.url());
                    LogUtil.i(TAG, original.method() + " ---> " + ServiceGenerator.toString(original));
                } catch (Exception e) {
                    LogUtil.e(TAG, "Message ---> " + e.getMessage());
                }

                AccessTokenModel accessTokenModel = accessTokenStorage.getFirst();

                // Request customization: add request headers
                if (accessTokenModel != null) {
                    Request.Builder requestBuilder = original.newBuilder().addHeader("Authorization", accessTokenModel.getAccess_token());
                    Request request = requestBuilder.build();
                    try {
                        String header = request.header("Authorization");
                        LogUtil.i(TAG, "Authorization  " + header);
                    } catch (Exception e) {
                        LogUtil.e(TAG, "Message ---> " + e.getMessage());
                    }
                    return chain.proceed(request);
                } else {
                    return chain.proceed(original.newBuilder().build());
                }
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .client(client)
                .build();

        return retrofit.create(clazz);
    }

    /**
     * GET Value Request
     *
     * @param request
     * @return
     */
    private static String toString(Request request) {
        try {
            if (request.method().equals("GET")) {
                return request.url().query();
            } else {
                final Request copy = request.newBuilder().build();
                if (copy != null) {
                    final Buffer buffer = new Buffer();
                    RequestBody body = copy.body();
                    if (body != null) {
                        body.writeTo(buffer);
                        return buffer.readUtf8();
                    }
                }
            }
            return "null";
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
