package com.example.lab5.services;

import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;
import com.example.lab5.model.User;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "http://172.16.55.231:3000/";

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
    @Multipart
    @POST("users/add")
    Call<Response<User>> register(@Part("username")RequestBody username,
                                  @Part("password") RequestBody password,
                                  @Part("Email") RequestBody Email,
                                  @Part("name") RequestBody name,
                                  @Part("age") RequestBody age,
                                  @Part("available") RequestBody available,
                                  @Part MultipartBody.Part avatar);
    @POST("login/checkLogin")
    Call<Response<User>> checkLogin(@Body User user);
}