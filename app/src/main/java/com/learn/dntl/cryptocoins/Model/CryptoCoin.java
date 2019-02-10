package com.learn.dntl.cryptocoins.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCoin {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price_usd")
    @Expose
    private String priceUsd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }
}