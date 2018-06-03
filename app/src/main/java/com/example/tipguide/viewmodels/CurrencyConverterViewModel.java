package com.example.tipguide.viewmodels;

import android.util.Log;
import com.example.tipguide.datamodels.CurrencyConverterAPIModel;
import com.example.tipguide.models.CurrencyConversion;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.function.Consumer;

public class CurrencyConverterViewModel {

    private CurrencyConverterAPIModel dataModel;
    private Gson gson;
    private static String TAG = CurrencyConverterAPIModel.class.getSimpleName();

    public CurrencyConverterViewModel(CurrencyConverterAPIModel model) {
        this.dataModel = model;
    }

    public void getCurrencyConversion(final Consumer<String> resultCallback) {
        this.dataModel.getRates(
                (JSONObject object) -> {
                    gson = new Gson();
                    Log.i(TAG, object.toString());
                    CurrencyConversion conversion = gson.fromJson(object.toString(), CurrencyConversion.class);
                    String value = String.valueOf(conversion.getRates().values());
                    resultCallback.accept(value);
                },
                error -> System.out.println("Something bad happened.")
        );
    }


}