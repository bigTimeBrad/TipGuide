package com.example.tipguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tipguide.datamodels.CurrencyConverterAPIModel;
import com.example.tipguide.viewmodels.CurrencyConverterViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ConverterFragment extends Fragment {

    public ConverterFragment() {
    }
    TextView currencyTitleTextView;
    TextView conversionTextView;
    public int to;
    public int from;
    public String[] val;
    public String s;
    public Handler handler;
    private Context context;
    private CurrencyConverterViewModel currencyConverterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_converter, container, false);
        currencyTitleTextView = rootView.findViewById(R.id.currency_title);
        conversionTextView = rootView.findViewById(R.id.textViewResult);
        Spinner s1 = rootView.findViewById(R.id.spinnerIn);
        Spinner s2 = rootView.findViewById(R.id.spinnerOut);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(this.getActivity()), R.array.name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        val = getResources().getStringArray(R.array.value);
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        s1.setOnItemSelectedListener(new spinOne(1));
        s2.setOnItemSelectedListener(new spinOne(2));
        Button b = rootView.findViewById(R.id.buttonConverter);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                if (from == to) {
                    Toast.makeText(getActivity().getApplicationContext(), "Choose A Different Currency", Toast.LENGTH_LONG).show();
                } else {
                    CurrencyConverterAPIModel dataModel = new CurrencyConverterAPIModel(val[from], val[to], context);
                    currencyConverterViewModel = new CurrencyConverterViewModel(dataModel);
                    currencyConverterViewModel.getCurrencyConversion(value -> conversionTextView.setText(String.valueOf(round(Double.valueOf(value.substring(1,value.length()-1)),3)).concat(" " + val[to])));
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public class spinOne implements AdapterView.OnItemSelectedListener {
        int ide;

        spinOne(int i) {
            ide = i;
        }

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int index, long id) {
            if (ide == 1)
                from = index;
            else if (ide == 2)
                to = index;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }
}