package co.com.harmonic.repository.impl;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.com.harmonic.domain.model.Instrument;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.repository.interfaces.InstrumentRepository;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public class InstrumentFirebaseRepository implements InstrumentRepository {
    private FirebaseDatabase firebaseDatabase;
    private List<Instrument> instrumentList;

    public InstrumentFirebaseRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        instrumentList = new ArrayList<>();
    }

    @Override
    public void getAllInstruments(final Callback<List<Instrument>> listCallback) {
        firebaseDatabase.getReference().child("instrumentos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        instrumentList.add(child.getValue(Instrument.class));
                    }
                    listCallback.success(instrumentList);
                } catch (Exception ex) {
                    listCallback.error(ex);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
}
