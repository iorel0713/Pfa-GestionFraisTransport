package com.example.gestionfraimobileandroid.networking;

import com.example.gestionfraimobileandroid.beans.Request;
import com.example.gestionfraimobileandroid.beans.ResponseAcceptedrequest;
import com.example.gestionfraimobileandroid.beans.ResponseAllrequest;
import com.example.gestionfraimobileandroid.beans.ResponseBloqueruser;
import com.example.gestionfraimobileandroid.beans.ResponseRefusedrequest;
import com.example.gestionfraimobileandroid.beans.ResponseWaitedRequest;
import com.example.gestionfraimobileandroid.beans.ResponsebtnAccteper;
import com.example.gestionfraimobileandroid.beans.User;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/api/v1/auth/login")
    Call<User> Authentifcate(@Body Map<String,String> options);

    @POST("/api/v1/auth/register")
    Call<User> Register(@Body User userRegister);

    @GET("/api/v1/requests")
    Call<ResponseAllrequest>allRequest(@Header("Authorization") String auth);

    //khedma adam

    @GET("api/v1/requests/accepted")
    Call<ResponseAcceptedrequest>acceptedRequest(@Header("Authorization")String auth);//ache tat3ni hadi akhay

    @GET("api/v1/requests/refused")
    Call<ResponseRefusedrequest>refusedRequest(@Header("Authorization")String auth);

    @GET("api/v1/requests/waited")
    Call<ResponseWaitedRequest>waitedRequest(@Header("Authorization")String auth);

    @POST("/api/v1/requests")
    Call<Object> creationdemande(@Body Request request,@Header("Authorization")String auth);

    @GET("/api/v1/requests/{idU}/to/accepted")
    Call<ResponsebtnAccteper>btnaccepter(@Path ("idU")int idU, @Header("Authorization")String auth);

    @GET("/api/v1/requests/{idU}/to/refused")
    Call<ResponsebtnAccteper>btnrefuser(@Path ("idU")int idU, @Header("Authorization")String auth);
    @GET("/api/v1/users")
    Call<ResponseAcceptedrequest>allusers(@Header("Authorization") String auth);
    @GET("/api/v1/users/{idU}/to/enable")
    Call<ResponseBloqueruser>btnbloquer(@Path ("idU")Long idU, @Header("Authorization")String auth);

    @GET("/api/v1/users/{idU}/to/disable")
    Call<ResponseBloqueruser>btndebloquer(@Path ("idU")Long idU, @Header("Authorization")String auth);
}