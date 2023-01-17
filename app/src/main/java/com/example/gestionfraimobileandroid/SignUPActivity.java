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

public class SignUPActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private EditText first_name;
    private EditText last_name;
    private EditText username;
    private EditText email;
    private EditText password;
    private TextView txtSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        txtSignIn=findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUPActivity.this,LoginActivity.class));
            }
        });
        Button btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class );
                //startActivity(intent);

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(SignUPActivity.this, "veuillez remplires toutes les champs", Toast.LENGTH_LONG).show();
                } else {
                    login();
                    startActivity(new Intent(SignUPActivity.this,LoginActivity.class));
                }
            }
        });
    }

    public void login() {
        User u = new User(first_name.getText().toString(),last_name.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> loginResponseCall = ApiClient.getUserService().Register(u);
        loginResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Log.e("adaaaaaaam", response.body().toString());


                }
                if (response.isSuccessful()) {
                    //  LoginResponse loginResponse = response.body();
                    Toast.makeText(SignUPActivity.this, "Sign up Successful", Toast.LENGTH_LONG).show();
                    vider();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //startActivity(new Intent(SignUPActivity.this,MainActivity.class));
                            //Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                            //token = response.body().getToken();
                        }
                    }, 700);

                } else {
                    Toast.makeText(SignUPActivity.this, "Login Failed ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUPActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("adaaaaaaaaaaaaaam",t.getLocalizedMessage());
            }
        });

    }
    public void vider(){
        first_name.setText("");
        last_name.setText("");
        username.setText("");
        email.setText("");
        password.setText("");
    }
}