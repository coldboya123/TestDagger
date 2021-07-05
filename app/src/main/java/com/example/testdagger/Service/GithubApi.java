package com.example.testdagger.Service;

import com.example.testdagger.Model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {
    @GET(value = "users/coldboya123")
    Call<Profile> getProfile();
}
