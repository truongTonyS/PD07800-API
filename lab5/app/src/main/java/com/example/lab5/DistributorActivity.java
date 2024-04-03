package com.example.lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DistributorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_distributor);

    }
    Callback<Response<ArrayList<Distributor>>>  getListDistributor = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {

        }
    }
}