package com.example.tipguide;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tipguide.model.CountryModel;
import com.example.tipguide.viewmodels.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private CountryRecyclerViewAdapter adapter;
    private CountryViewModel countryViewModel;
    private OnListFragmentInteractionListener mListener;

    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "matches";
    private int mColumnCount = 6;
    private List<CountryModel> mMatches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        countryViewModel = new CountryViewModel();

        adapter = new CountryRecyclerViewAdapter(mMatches, mListener);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        countryViewModel.getDataFromViewModel(
                (ArrayList<CountryModel> country) -> {
                    adapter.updateCountryListItems(country);
                }
        );
        return recyclerView;
    }
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CountryModel item);
    }
}
