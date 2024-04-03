package com.example.lab5.services;

import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;

import java.util.ArrayList;

import retrofit2.Call;

import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL ="http://172.16.55.251:3000/";
    @GET("distributors/list")
    Call<Response<ArrayList<Distributor>>> getListDistributor();
}
