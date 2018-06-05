package com.example.tipguide;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tipguide.models.CountryModel;
import com.example.tipguide.viewmodels.CountryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private CountryViewModel countryViewModel;
    private OnListFragmentInteractionListener mListener;
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "matches";
    private int mColumnCount = 6;
    private List<CountryModel> mMatches;
    private View rootView;
    CountryModel chosenCountry;
    public String[] val;
    public String s;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Context context;
    int countryIndex;
    private static String TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
    }

    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        Log.i(TAG, "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mMatches = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }
        Log.i(TAG, "onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_home, container, false);
        textView1 = rootView.findViewById(R.id.textView1);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        textView4 = rootView.findViewById(R.id.textView4);
        Spinner s1 = rootView.findViewById(R.id.spinner1);
        Spinner s2 = rootView.findViewById(R.id.spinner2);
        Button updateCountryButton = rootView.findViewById(R.id.update);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(this.getActivity()), R.array.name2, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        val = getResources().getStringArray(R.array.name2);
        s1.setAdapter(arrayAdapter);
        //s1.setOnItemSelectedListener(new HomeFragment.spinOne(1));
        //Button b = rootView.findViewById(R.id.button1);

        //button on click listener.
        updateCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        int countrySelected = s1.getSelectedItemPosition();

        Log.v("s1", String.valueOf(s1));

        countryViewModel = new CountryViewModel();
        // Set the adapter
        countryViewModel.getDataFromViewModel(
                (ArrayList<CountryModel> countries) -> {
                    //CountryRecyclerViewAdapter adapter = new CountryRecyclerViewAdapter(country, mListener);
                    String country = val[countrySelected];
                    //Log.v("coutnry", country);
                    for (CountryModel model : countries) {
                        if (model.name.equals(country)) {
                            chosenCountry = model;
                            textView1.setText("Country: \n" + chosenCountry.name + "\n");
                            textView2.setText("Restaurant tip: \n" + chosenCountry.restTip + "\n");
                            textView3.setText("Taxi Ride tip: \n" + chosenCountry.taxiGuideTip + "\n");
                            textView4.setText("Hotel tip : \n" + chosenCountry.hotelTip + "\n");
                        }
                    }
                }
        );

            }
        });
        return rootView;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CountryModel item);
    }

    public class spinOne implements AdapterView.OnItemSelectedListener {
        int ide;
        spinOne(int i)
        {
            ide =i;
        }
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int index, long id) {
            if(ide == 1) {
                countryIndex = index;
            }

        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i(TAG, "onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }



}