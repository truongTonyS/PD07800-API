package com.example.lab5.services;

import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;

import java.util.ArrayList;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL ="http://172.16.49.129:3000/";
    @GET("distributors/list")
    Call<Response<ArrayList<Distributor>>> getListDistributor();


    @POST("distributors/add")
    Call<Response<Distributor>> addDistriburor(@Body Distributor distributor);
    @PUT("distributors/edit/{id}")
    Call<Response<Distributor>> updateDistriburor(@Path("id") String id, @Body Distributor distributor);
    @DELETE("distributors/delete/{id}")
    Call<Response<Distributor>> deleteDistriburor(@Path("id") String id);
    @GET("distributors/search")
    Call<Response<ArrayList<Distributor>>> searchListDistributor(@Query("key") String key);
}
