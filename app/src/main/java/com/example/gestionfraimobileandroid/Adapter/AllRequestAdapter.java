package com.example.gestionfraimobileandroid.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gestionfraimobileandroid.MainActivity;
import com.example.gestionfraimobileandroid.R;
import com.example.gestionfraimobileandroid.beans.Request;

import java.util.ArrayList;
import java.util.List;

public class AllRequestAdapter extends BaseAdapter {
    private List<Request> objects;
    private LayoutInflater inflater;

    public AllRequestAdapter(Activity activity, List<Request> objects) {
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public List<Request> getObjects() {
        return objects;
    }


    public AllRequestAdapter(MainActivity activity) {
        this.objects = new ArrayList<Request>();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int request, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.iteam_allrequest, null);


        TextView id_Req = convertView.findViewById(R.id.id_Req);
        TextView txt_reason = convertView.findViewById(R.id.txt_reason);
        TextView txt_destcity = convertView.findViewById(R.id.txt_destcity);
        TextView txttotalfees = convertView.findViewById(R.id.txt_totalfees);
        Log.i("Adapter",objects.get(request).getReason());
        int idd=objects.get(request).getId();
        String addd= String.valueOf(Integer.parseInt(String.valueOf(idd)));
        id_Req.setText(addd);
       txt_reason.setText(objects.get(request).getReason());
        txt_destcity.setText(objects.get(request).getDestination_city());
        Float d = objects.get(request).getTotal_fees();
        String s = String.valueOf(Float.parseFloat(String.valueOf(d)));
        txttotalfees.setText(s);
        return convertView;
    }

    public void setObjects(List<Request> requests) {

        this.objects=requests;
        Log.i("Adapter", this.objects.toString());
        Log.i("Adapter", this.objects.toString());
    }
}