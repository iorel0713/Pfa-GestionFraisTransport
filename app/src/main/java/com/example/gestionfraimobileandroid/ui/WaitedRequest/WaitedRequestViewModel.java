package com.example.gestionfraimobileandroid.ui.WaitedRequest;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.beans.ResponseAllrequest;
import com.example.gestionfraimobileandroid.beans.ResponseWaitedRequest;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;
import com.example.gestionfraimobileandroid.networking.Crendentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitedRequestViewModel extends AndroidViewModel {

    static MutableLiveData<List<Request>> mRequest = new MutableLiveData<>();

    private static final String TAG = "WaitedRequestViewModel";

    public WaitedRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Request>> getRequests() {
        return mRequest;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseWaitedRequest> call = apiInterface.waitedRequest(Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponseWaitedRequest>() {
            @Override
            public void onResponse(Call <ResponseWaitedRequest>call, Response<ResponseWaitedRequest> response) {
                //Log.d(TAG, "onResponse: " + response);
                mRequest.setValue((List<Request>) response.body().getData().getRequests());
                Log.d(TAG, "ADAAAAM " + response.body().toString());
                Log.d(TAG, "getValeu(à: " + mRequest.getValue());
            }

            @Override
            public void onFailure(Call <ResponseWaitedRequest>call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
    public static Request getRequestbyid(int req_id) {
        for (Request e : mRequest.getValue()) {
            if (e.getId() == req_id) {
                return e;
            }
        }
        return null;
    }





}
