package com.app.cbfc.network;

import com.app.cbfc.util.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 100;
    private static OkHttpClient okHttpClient;
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private  static APIRequest api_;
    public  static APIRequest getInstance()
    {
        if(api_ ==null)
        {
            /*OkHttpClient okHttpClient= new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60,TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .com.app.cbfc.ui.activity.build();*/
            if (okHttpClient == null)
                initOkHttp();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            /*Retrofit retrofit= new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://101.53.139.53:28080/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okHttpClient)
                    .com.app.cbfc.ui.activity.build();*/
            APIRequest api_ =  retrofit.create(APIRequest.class);
            return api_;

        }
        else
            return api_;
    }

    public static Retrofit getClient() {

        if (okHttpClient == null)
            initOkHttp();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static void initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json; charset=utf-8")
                        .addHeader("Content-Type", "application/json; charset=utf-8");

                // Adding Authorization token (API Key)
                // Requests will be denied without API key

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        okHttpClient = httpClient.build();
    }


}
