package com.learn.dntl.cryptocoins.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static APIService getSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.URL_Base)//get's URL (from APIService.class)
                .addConverterFactory(GsonConverterFactory.create())//i don't know, converts?
                .build();//building

        return retrofit.create(APIService.class);
    }
}
