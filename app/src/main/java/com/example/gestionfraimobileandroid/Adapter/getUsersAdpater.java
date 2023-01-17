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
import com.example.gestionfraimobileandroid.beans.ResponseAcceptedrequest;
import com.example.gestionfraimobileandroid.beans.User;

import java.util.ArrayList;
import java.util.List;

public class getUsersAdpater extends BaseAdapter {
    private List<User> objects;
    private LayoutInflater inflater;

    public getUsersAdpater(Activity activity, List<User> objects) {
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public List<User> getObjects() {
        return objects;
    }


    public getUsersAdpater(MainActivity activity) {
        this.objects = new ArrayList<User>();
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
    public View getView(int user, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.iteam_allusers, null);


        TextView id_Req = convertView.findViewById(R.id.id_Req);
        TextView txt_username = convertView.findViewById(R.id.txt_username);
        TextView txt_last_name = convertView.findViewById(R.id.txt_last_name);
        TextView txt_email = convertView.findViewById(R.id.txt_email);
        Log.i("Adapter",objects.get(user).getUsername());
        Long idd=objects.get(user).getId();
        String addd= String.valueOf(Integer.parseInt(String.valueOf(idd)));
        id_Req.setText(addd);
        txt_username.setText(objects.get(user).getUsername());
        txt_last_name.setText(objects.get(user).getLast_name());
        txt_email.setText(objects.get(user).getEmail());

        return convertView;
    }

    public void setObjects(List<User> users) {

        this.objects=users;
        Log.i("Adapter", this.objects.toString());
        Log.i("Adapter", this.objects.toString());
    }
}

