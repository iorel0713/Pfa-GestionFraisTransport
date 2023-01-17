package com.example.gestionfraimobileandroid.ui.getUsers;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.beans.ResponseAcceptedrequest;
import com.example.gestionfraimobileandroid.beans.ResponseAllrequest;
import com.example.gestionfraimobileandroid.beans.User;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;
import com.example.gestionfraimobileandroid.networking.Crendentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllUsersViewModel extends AndroidViewModel {

    static MutableLiveData<List<User>> mUsers = new MutableLiveData<>();

    private static final String TAG = "alLUSERS";

    public AllUsersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<User>> getUser() {
        return mUsers;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAcceptedrequest> call = apiInterface.allusers(Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponseAcceptedrequest>() {
            @Override
            public void onResponse(Call <ResponseAcceptedrequest>call, Response<ResponseAcceptedrequest> response) {
                //Log.d(TAG, "onResponse: " + response);
                mUsers.setValue((List<User>) response.body().getData().getUsers());
               // Log.d(TAG,  response.body().toString());
                //Log.d(TAG, "getValeu(à: " + mUsers.getValue());
            }

            @Override
            public void onFailure(Call <ResponseAcceptedrequest>call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
    public static User getUserbyid(Long user_id) {
        for (User e : mUsers.getValue()) {
            if (e.getId() == user_id) {
                return e;
            }
        }
        return null;
    }





}
