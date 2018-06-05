package com.example.tipguide.datamodels;
//Note: In order for firebase to work, must add implementations in gradle.
import android.util.Log;

import com.example.tipguide.models.CountryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

public class CountryDataModel {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public CountryDataModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void getDataFromDataModel(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        // This is where we can construct our path
        DatabaseReference dataFirebaseRef = mDatabase.child("countries");
        ValueEventListener dataFirebaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
                Log.i("dataSnapshotInsideModel: ", dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }
        };
        dataFirebaseRef.addValueEventListener(dataFirebaseListener);
        listeners.put(dataFirebaseRef, dataFirebaseListener);
    }

    //updates person by id
    public void updateCountryItemById(CountryModel country) {
        DatabaseReference matchesItemsRef = mDatabase.child("countries");
        matchesItemsRef.child(country.id).setValue(country);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }

}