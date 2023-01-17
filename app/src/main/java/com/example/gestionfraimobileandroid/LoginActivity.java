package com.example.gestionfraimobileandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionfraimobileandroid.beans.User;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private TextView txt_signup;
    public static  SharedPreferences sharref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        txt_signup=findViewById(R.id.txt_signup);
txt_signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(LoginActivity.this,SignUPActivity.class));
    }
});
        Button loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class );
                //startActivity(intent);

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    login();
                }
            }
        });
    }
    public void login() {
        Map<String, String> userdata = new HashMap<>();
        userdata.put("username", username.getText().toString());
        userdata.put("password", password.getText().toString());
        sharref = getSharedPreferences("accessToken", Context.MODE_PRIVATE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> loginResponseCall = ApiClient.getUserService().Authentifcate(userdata);
        loginResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {

                   String accessToken =   response.body().getData().getAccessToken();
                    Log.e("adaaaaaaam", response.body().getData().getAccessToken());
                    SharedPreferences.Editor myEditor = sharref.edit();
                   myEditor.putString("accessToken", accessToken);
                   myEditor.commit();
                   // Log.i("accessToken", accessToken);
                }
                if (response.isSuccessful()) {
                  //  LoginResponse loginResponse = response.body();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            //Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                            //token = response.body().getToken();
                        }
                    }, 700);

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("adaaaaaaaaaaaaaam",t.getLocalizedMessage());
            }
        });

    }
}