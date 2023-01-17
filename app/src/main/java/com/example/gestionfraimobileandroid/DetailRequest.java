package com.example.gestionfraimobileandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.beans.ResponseAllrequest;
import com.example.gestionfraimobileandroid.beans.ResponsebtnAccteper;
import com.example.gestionfraimobileandroid.networking.ApiClient;
import com.example.gestionfraimobileandroid.networking.ApiInterface;
import com.example.gestionfraimobileandroid.networking.Crendentials;
import com.example.gestionfraimobileandroid.ui.AllRequest.AllRequestViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRequest extends AppCompatActivity {
    private int txt_ids;
    private TextView txt_id,txt_reason,txt_transport,txt_start_date,txt_end_date,txt_destination_city,txt_total_fees,txt_status,txt_userId;
private Button btn_accepter,btn_refuser;
    public static Request r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_request);
        Intent i = getIntent();
        txt_ids = i.getIntExtra("request_id", -1);

        Log.i("Moo", String.valueOf(txt_id));
        txt_id = findViewById(R.id.txt_id);
        txt_reason = findViewById(R.id.txt_reason);
        txt_transport = findViewById(R.id.txt_transport);
        txt_start_date = findViewById(R.id.txt_start_date);
        txt_end_date = findViewById(R.id.txt_end_date);
        txt_destination_city = findViewById(R.id.txt_destination_city);
        txt_total_fees = findViewById(R.id.txt_total_fees);
        txt_status = findViewById(R.id.txt_status);

        txt_userId = findViewById(R.id.txt_userId);

       r =  AllRequestViewModel.getRequestbyid(txt_ids);
        txt_id.setText("id " +r.getId());
        /*
          Float d = objects.get(request).getTotal_fees();
        String s = String.valueOf(Float.parseFloat(String.valueOf(d)));
        * */
        txt_reason.setText("Reason "+r.getReason());
        txt_transport.setText("Transport "+r.getTransport());
       /* Date sd = r.getStart_date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String std = sdf.format("Star tDate "+sd);*/
        Date ssd = r.getStart_date();
        String sstd = ssd.toString();
        txt_start_date.setText("Start Date  "+r.getStart_date());

       /* Date ed = r.getEnd_date();
        SimpleDateFormat edf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String etd = edf.format("End Date "+ed);
*/
        Date eed = r.getEnd_date();
        String eetd = eed.toString();
        txt_end_date.setText("End Date  "+r.getEnd_date());
        txt_destination_city.setText("Destination_city "+r.getDestination_city());
  /*
          Float d = objects.get(request).getTotal_fees();
        String s = String.valueOf(Float.parseFloat(String.valueOf(d)));
        * */

        float totalFees = r.getTotal_fees();
        String totalFeesString = String.valueOf(totalFees);
        txt_total_fees.setText("Total fees "+totalFeesString);

        txt_status.setText("Status "+r.getStatus());
        txt_userId.setText("Userid "+r.getUserId());

        //Parse all types to String
        //add Commentes

        btn_accepter=findViewById(R.id.btn_accepter);
        btn_accepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });







        btn_refuser=findViewById(R.id.btn_refuser);
        btn_refuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init2();
            }
        });
    }
    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsebtnAccteper> call = apiInterface.btnaccepter(r.getId(),Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponsebtnAccteper>() {
            @Override
            public void onResponse(Call <ResponsebtnAccteper>call, Response<ResponsebtnAccteper> response) {
                Log.d("TAG", "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call <ResponsebtnAccteper>call, Throwable t) {
                startActivity(new Intent(DetailRequest.this,MainActivity.class));
            }
        });
}
    void init2() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsebtnAccteper> call = apiInterface.btnrefuser(r.getId(),Crendentials.getaccess_token());
        call.enqueue(new Callback<ResponsebtnAccteper>() {
            @Override
            public void onResponse(Call <ResponsebtnAccteper>call, Response<ResponsebtnAccteper> response) {
                Log.d("TAG", "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call <ResponsebtnAccteper>call, Throwable t) {
                startActivity(new Intent(DetailRequest.this,MainActivity.class));
            }
        });
    }
}