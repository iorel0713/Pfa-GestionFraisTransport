package com.example.gestionfraimobileandroid.ui.creationdemande;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionfraimobileandroid.LoginActivity;
import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.beans.ResponseAcceptedrequest;
import com.example.gestionfraimobileandroid.beans.User;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;
import com.example.gestionfraimobileandroid.networking.Crendentials;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationDemandeFragment extends Fragment {


    EditText reasonn, destination_city, total_fees, start_date, end_date, transport;
    Button btnAjouter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creation_demande, container, false);
        reasonn = view.findViewById(R.id.reason);
        destination_city = view.findViewById(R.id.destination_city);
        total_fees = view.findViewById(R.id.total_fees);
        start_date = view.findViewById(R.id.start_date);
        end_date = view.findViewById(R.id.end_date);
        transport = view.findViewById(R.id.transport);
        btnAjouter=view.findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    create();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    public void create() throws ParseException {
        float t = Float.parseFloat(total_fees.getText().toString());
        String startDateText = start_date.getText().toString();
        Toast.makeText(getContext(), "Created", Toast.LENGTH_SHORT).show();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateText);
        String enddate = end_date.getText().toString();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date ed = dateFormat.parse(enddate);
        String reason=reasonn.getText().toString();

        Request request = new Request(reason, transport.getText().toString(), startDate, ed, destination_city.getText().toString(), t, 1);
        Log.e("fefesfseff",request.toString());
        Call<Object> o = ApiClient.getUserService().creationdemande(request, Crendentials.getaccess_token());
        o.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

   if( response.body()!=null) {
       Log.e("adaaaaaaaaaaaaaam", response.body().toString());

   }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("adaaaaaaaaaaaaaam", t.getLocalizedMessage());
            }
        });
    }
}