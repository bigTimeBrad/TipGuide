package com.example.tipguide.viewmodels;

import android.util.Log;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.example.tipguide.datamodels.CountryDataModel;
import com.example.tipguide.model.CountryModel;
import com.google.firebase.database.DataSnapshot;

public class CountryViewModel {

    private CountryDataModel datamodel;

    public CountryViewModel() {
        datamodel = new CountryDataModel();
    }

    public void getDataFromViewModel(Consumer<ArrayList<CountryModel>> resultCallback) {
        datamodel.getDataFromDataModel(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<CountryModel> arrayOfusers= new ArrayList<>();
                    for (DataSnapshot matchesSnapshot : dataSnapshot.getChildren()) {
                        Log.i("dataSnapshotInsideViewModel: ", matchesSnapshot.getKey());
                        CountryModel matches = matchesSnapshot.getValue(CountryModel.class);
                        assert matches != null;
                        matches.uid = matchesSnapshot.getKey();
                        arrayOfusers.add(matches);
                    }
                    resultCallback.accept(arrayOfusers);
                },
                (databaseError -> System.out.println("Error reading Firebase data: " + databaseError))
        );
    }
    //updates the item in the database
    public void updateCountryListItems(CountryModel country) {
        datamodel.updateCountryItemById(country);
    }
    public void clear() {
        datamodel.clear();
    }

}
