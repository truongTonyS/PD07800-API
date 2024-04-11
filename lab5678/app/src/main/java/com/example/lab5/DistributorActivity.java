package com.example.lab5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lab5.adapter.DistributorsAdapter;
import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;
import com.example.lab5.services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DistributorActivity extends AppCompatActivity {
    List<Distributor> list;
    EditText edSearch;
    HttpRequest httpRequest;
    Dialog dialog;
    EditText edNameDistributor;
    Button btnSaveDialog, btnCanceDialog;
    FloatingActionButton floatingActionButton;
    String TAG = "//===DistributorActivity";
    private RecyclerView recyclerView;
    private DistributorsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpRequest = new HttpRequest();
        setContentView(R.layout.activity_distributor);
        edSearch = findViewById(R.id.edSearch);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floadActionButon);
        recyclerView = (RecyclerView) findViewById(R.id.rcvDistriButor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DistributorsAdapter();
        adapter.setonItemClickListener(new DistributorsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
            showDialogDelete(id);
            }

            @Override
            public void updateItem(String id, String name) {

                openDialog(id, name);
            }
        });
        onResume();;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("","");
            }
        });
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String key = edSearch.getText().toString().trim();
                    httpRequest.callApi().searchListDistributor(key).enqueue(searchDistributor);
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        httpRequest.callApi().getListDistributor().enqueue(getListDistributor);
    }

    Callback<Response<ArrayList<Distributor>>>  getListDistributor = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    list = new ArrayList<>();
                    list = response.body().getData();
                    adapter.setData(list);
                    recyclerView.setAdapter(adapter);
                    for(Distributor item: list){
                             Log.i(TAG, "//==="+item.toString());
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {
            Log.i(TAG, "//==Error="+throwable.getMessage());
        }
    };
    Callback<Response<Distributor>> addDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus()==200){
                    Toast.makeText(getApplicationContext(),"Add successful", Toast.LENGTH_SHORT).show();
                    onResume();
                    dialog.dismiss();
                }
            }
        }


        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG, "//==Error="+throwable.getMessage());
        }
    };
    Callback<Response<Distributor>> updateDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus()==200){
                    Toast.makeText(getApplicationContext(),"update successful", Toast.LENGTH_SHORT).show();
                    onResume();
                    dialog.dismiss();
                }
            }
        }


        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG, "//==Error="+throwable.getMessage());
        }
    };
    public void openDialog(String id, String name){
        dialog = new Dialog(DistributorActivity.this);
        dialog.setContentView(R.layout.dialog_distrubutor); // Sử dụng layout của dialog khác

        edNameDistributor = dialog.findViewById(R.id.edName);
        edNameDistributor.setText(name);
        btnSaveDialog = dialog.findViewById(R.id.btnSave);
        btnCanceDialog = dialog.findViewById(R.id.btnCancel);

        btnCanceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = edNameDistributor.getText().toString().trim();
                if (strName.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please input Distributor", Toast.LENGTH_SHORT).show();
                } else {
                    // Tạo một đối tượng Distributor mới
                    Distributor distributor = new Distributor();
                    distributor.setName(strName);
                    if (id.isEmpty()){
                        httpRequest.callApi().addDistriburor(distributor).enqueue(addDistributor);
                    }else{
                        httpRequest.callApi().updateDistriburor(id,distributor).enqueue(updateDistributor);
                    }



                    // Đóng dialog sau khi thêm
                    dialog.dismiss();
                }
            }
        });

        // Hiển thị dialog
        dialog.show();
    }
    Callback<Response<Distributor>> deleteDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus()==200){
                    Toast.makeText(getApplicationContext(),"delete successful", Toast.LENGTH_SHORT).show();
                    onResume();
                    dialog.dismiss();
                }
            }
        }


        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG, "//==Error="+throwable.getMessage());
        }
    };
    public  void showDialogDelete(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(DistributorActivity.this);
        builder.setTitle("Delete Distributor");
        builder.setMessage("Are you sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                httpRequest.callApi().deleteDistriburor(id).enqueue(deleteDistributor);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    Callback<Response<ArrayList<Distributor>>>  searchDistributor = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    list = new ArrayList<>();
                    list = response.body().getData();
                    adapter.setData(list);
                    recyclerView.setAdapter(adapter);
                    for(Distributor item: list){
                        Log.i(TAG, "//==="+item.toString());
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {
            Log.i(TAG, "//==Error="+throwable.getMessage());
        }
    };

}