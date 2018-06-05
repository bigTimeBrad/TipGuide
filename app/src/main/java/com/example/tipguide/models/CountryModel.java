package com.example.tipguide.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryModel implements Parcelable {
    public String id;
    public String hotelTip;
    public String name;
    public String restTip;
    public String taxiGuideTip;

    public CountryModel(){}

    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {

        @Override
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel();
        }

        @Override
        public CountryModel[] newArray(int size) {

            return new CountryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hotelTip);
        dest.writeString(name);
        dest.writeString(restTip);
        dest.writeString(taxiGuideTip);

    }
}