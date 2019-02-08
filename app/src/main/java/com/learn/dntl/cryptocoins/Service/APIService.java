package com.learn.dntl.cryptocoins.Service;

import com.learn.dntl.cryptocoins.Model.CryptoCoin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    String URL_Base = "https://api.coinmarketcap.com/v1/";

    @GET("ticker")
    Call<List<CryptoCoin>> getCryptoCoins();
}
