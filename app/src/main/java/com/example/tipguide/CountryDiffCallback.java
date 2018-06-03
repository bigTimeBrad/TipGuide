package com.example.tipguide;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.tipguide.model.CountryModel;
import java.util.List;

public class CountryDiffCallback extends DiffUtil.Callback{

        private final List<CountryModel> mOldCountryList;
        private final List<CountryModel> mNewCountryList;

        public CountryDiffCallback(List<CountryModel> oldCountryList, List<CountryModel> newCountryList) {
            this.mOldCountryList = oldCountryList;
            this.mNewCountryList = newCountryList;
        }

        @Override
        public int getOldListSize() {
            return mOldCountryList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewCountryList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldCountryList.get(oldItemPosition).uid == mNewCountryList.get(
                    newItemPosition).uid;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final CountryModel oldCountry = mOldCountryList.get(oldItemPosition);
            final CountryModel newCountry = mNewCountryList.get(newItemPosition);

            return oldCountry.name.equals(newCountry.name);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }

}
