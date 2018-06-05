package com.example.tipguide;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorFragment extends Fragment {

     TextView firstBinary;
     EditText totalBillAmount;
     SeekBar tipPercent;
     SeekBar tipsNumberOfPeople;
     TextView totalAmountToBePaid;
     TextView totalAmountOfTipsToBePaid;
     TextView tipsPerPerson;
     Button calculateTips;
     int tipPercentValue = 0;
     int tipsForNumberOfPeople = 0;
     TextView tipPercentLabel;
     TextView splitNumberLabel;
     TextView cardText;





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.item_calculator, null);
        View view = inflater.inflate(R.layout.item_calculator, null);
        firstBinary = view.findViewById(R.id.first_binary);
        totalBillAmount = view.findViewById(R.id.bill_value);
        tipPercent = view.findViewById(R.id.seekBar);
        tipsNumberOfPeople = view.findViewById(R.id.seekBar_one);
        //cardText = view.findViewById(R.id.card_text);
        totalAmountToBePaid = view.findViewById(R.id.total_to_pay_result);
        totalAmountOfTipsToBePaid = view.findViewById(R.id.total_tip_result);
        tipsPerPerson = view.findViewById(R.id.tip_per_person_result);
        tipPercentLabel = view.findViewById(R.id.tip_percent);
        splitNumberLabel = view.findViewById(R.id.split_number);

        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipPercentLabel.setText("Tip Percent - " + seekBar.getProgress());
            }
        });
        tipsNumberOfPeople.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipsForNumberOfPeople = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                splitNumberLabel.setText("Split Number - " + seekBar.getProgress());
            }
        });
        calculateTips = (Button) view.findViewById(R.id.calculate_tips);
        calculateTips.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (totalBillAmount.getText().toString().equals("") || totalBillAmount.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "All Input field must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                double totalBillInput = Double.parseDouble(totalBillAmount.getText().toString());
                if (tipPercentValue == 0 || tipsForNumberOfPeople == 0) {
                    Toast.makeText(getActivity(), "Set values for Tip percent and split number", Toast.LENGTH_LONG).show();
                    return;
                }
                double percentageOfTip = (totalBillInput * tipPercentValue) / 100;
                double totalAmountForTheBill = totalBillInput + percentageOfTip;
                double tipPerEachPerson = percentageOfTip / tipsForNumberOfPeople;
                totalAmountToBePaid.setText(removeTrailingZero(String.valueOf(String.format("%.2f", totalAmountForTheBill))));
                totalAmountOfTipsToBePaid.setText(removeTrailingZero(String.valueOf(String.format("%.2f", percentageOfTip))));
                tipsPerPerson.setText(removeTrailingZero(String.valueOf(String.format("%.2f", tipPerEachPerson))));
            }
        });
        return view;

    }

    public String removeTrailingZero(String formattingInput) {
        if (!formattingInput.contains(".")) {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if (newValue.startsWith(".0")) {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
    }
}

