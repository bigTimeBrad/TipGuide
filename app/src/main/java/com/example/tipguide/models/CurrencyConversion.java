package com.example.tipguide.models;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConversion {
    @SerializedName("base")
    private String base;
    @SerializedName("date")
    private String date;
    @SerializedName("rates")
    private Map<String, Double> rates;

    public CurrencyConversion() {
        rates = new HashMap<>();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
