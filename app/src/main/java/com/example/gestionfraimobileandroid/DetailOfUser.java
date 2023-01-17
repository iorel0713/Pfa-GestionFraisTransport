package com.example.gestionfraimobileandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionfraimobileandroid.beans.ResponseAcceptedrequest;
import com.example.gestionfraimobileandroid.beans.ResponseBloqueruser;
import com.example.gestionfraimobileandroid.beans.User;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;
import com.example.gestionfraimobileandroid.networking.Crendentials;
import com.example.gestionfraimobileandroid.ui.getUsers.AllUsersViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOfUser extends AppCompatActivity {
    private Long txt_ids;
    private TextView txt_id,txt_first_name,txt_last_name,txt_username,txt_email,txt_status,txt_userId;
    private Button btn_bloquer,btn_debloquer;
    public static User r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_user);
        Intent i = getIntent();
        txt_ids = i.getLongExtra("user_id", -1);
        Log.e("TAG", "iiiiid = " + txt_ids);
        Log.i("Moo", String.valueOf(txt_id));
        txt_first_name = findViewById(R.id.txt_first_name);
        txt_last_name = findViewById(R.id.txt_last_name);
        txt_username = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);

        txt_status = findViewById(R.id.txt_status);
       // txt_userId = findViewById(R.id.txt_userId);

        r = AllUsersViewModel.getUserbyid(txt_ids);



      txt_first_name.setText("First name "+r.getFirst_name());
       txt_last_name.setText("last name "+r.getLast_name());
        txt_username.setText("username "+r.getUsername());
        txt_email.setText("Email "+r.getEmail());

         txt_status.setText("Status "+r.getStatus());
        //  txt_userId.setText("Userid "+r.getUserId());

        //Parse all types to String
        //add Commentes

        btn_bloquer=findViewById(R.id.btn_bloquer);
        btn_bloquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        btn_debloquer=findViewById(R.id.btn_debloquer);
        btn_debloquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init2();
            }
        });
    }
    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBloqueruser> call = apiInterface.btnbloquer(r.getId(),Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponseBloqueruser>() {
            @Override
            public void onResponse(Call <ResponseBloqueruser>call, Response<ResponseBloqueruser> response) {
                //Log.d(TAG, "onResponse: " + response);
                if(response.body()!=null){
                    Toast.makeText(DetailOfUser.this, "Debloqué", Toast.LENGTH_SHORT).show();
                    txt_status.setText("Status Enabled");
                }
                // Log.d(TAG,  response.body().toString());
                //Log.d(TAG, "getValeu(à: " + mUsers.getValue());
            }

            @Override
            public void onFailure(Call <ResponseBloqueruser>call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
    void init2() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBloqueruser> call = apiInterface.btndebloquer(r.getId(),Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponseBloqueruser>() {
            @Override
            public void onResponse(Call <ResponseBloqueruser>call, Response<ResponseBloqueruser> response) {
                //Log.d(TAG, "onResponse: " + response);
                if(response.body()!=null){
                    Toast.makeText(DetailOfUser.this, "Bloqué", Toast.LENGTH_SHORT).show();
                    txt_status.setText("Status Disabled");
                }
                // Log.d(TAG,  response.body().toString());
                //Log.d(TAG, "getValeu(à: " + mUsers.getValue());
            }

            @Override
            public void onFailure(Call <ResponseBloqueruser>call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
}