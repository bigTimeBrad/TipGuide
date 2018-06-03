package com.example.tipguide.datamodels;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class CurrencyConverterAPIModel {

    private String currencyConverterBaseUrl;
    private RequestQueue queue;

//    public CurrencyConverterAPIModel(Context context) {
//        queue = Volley.newRequestQueue(context);
//        currencyConverterBaseUrl ="https://exchangeratesapi.io/api/latest?base=USD&symbols=EUR";
//    }

    public CurrencyConverterAPIModel(String currencyFrom, String currencyTo, Context context) {
        queue = Volley.newRequestQueue(context);
        currencyConverterBaseUrl ="https://exchangeratesapi.io/api/latest?base=" + currencyFrom + "&symbols=" + currencyTo;
    }

//    public void getRates(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
//        JsonArrayRequest request = new JsonArrayRequest(currencyConverterBaseUrl, listener, errorListener);
//        queue.add(request);
//    }

    public void getRates(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, currencyConverterBaseUrl, null, listener, errorListener);
        queue.add(request);
    }

}
