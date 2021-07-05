package com.example.testdagger.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {
    public static GithubService instance = null;
    public static Retrofit retrofit = null;
    public static GithubApi githubApi = null;

    private GithubService(){
        retrofit = createRetrofit();
        githubApi =retrofit.create(GithubApi.class);
    }

    public static GithubService getInstance(){
        if (instance == null){
            instance = new GithubService();
        }
        return instance;
    }

    private Retrofit createRetrofit(){
        Retrofit retrofit = null;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static GithubApi getGithubApi(){
        return githubApi;
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }

}
