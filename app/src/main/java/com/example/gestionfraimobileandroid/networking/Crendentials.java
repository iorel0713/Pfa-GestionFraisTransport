package com.example.gestionfraimobileandroid.networking;

import com.example.gestionfraimobileandroid.LoginActivity;


public class Crendentials {
    final public static String accessToken = "";

    public static String getaccess_token() {
        return LoginActivity.sharref.getString("accessToken", "");
    }
}
