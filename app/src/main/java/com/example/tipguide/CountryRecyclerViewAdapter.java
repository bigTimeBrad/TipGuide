package com.example.tipguide;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tipguide.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder>
{
    private List<CountryModel> country;
    private final HomeFragment.OnListFragmentInteractionListener mListener;

    private static final String TAG = MainActivity.class.getSimpleName();

    public CountryRecyclerViewAdapter(List<CountryModel> items, HomeFragment.OnListFragmentInteractionListener listener) {
        this.country = items;
        this.mListener = listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.countrymodel = country.get(position);
        holder.name.setText(country.get(position).name);


    }

   @Override
    public int getItemCount() {

        if(country != null) {
            return country.size();
        } else {
            Log.i(TAG, "getItemCount() equals zero");
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public ImageButton btnlike;
        public CountryModel countrymodel;
        public Boolean liked;
        public final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.country);
        }
    }

    public void updateCountryListItems(List<CountryModel> country) {
        if (country == null) {
            country = new ArrayList<>();
        }
        final CountryDiffCallback diffCallback = new CountryDiffCallback(this.country, country);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.country.clear();
        this.country.addAll(country);
        diffResult.dispatchUpdatesTo(this);
    }
}
