package com.hfad.newfilms;

import android.app.Application;

import com.hfad.newfilms.service.FilmsService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    public FilmsService filmsService;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();
    }

    public static App getInstance() {
        return instance;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //важен порядок Interceptors
                .addInterceptor(new Interceptor() {

                    //передаем в Interceptor Header c token
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain
                                .request()
                                .newBuilder()
                                .addHeader("X-Auth-Token", "547ijfktj45")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(httpLoggingInterceptor) // add interceptor, see in Logcat
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://my-json-server.typicode.com/Ekaterina96Sergeevna/PlaceholderFilms/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        filmsService = retrofit.create(FilmsService.class);
    }


}
