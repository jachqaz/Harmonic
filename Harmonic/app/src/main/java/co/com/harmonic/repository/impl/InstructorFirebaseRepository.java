package co.com.harmonic.repository.impl;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.repository.interfaces.InstructorRepository;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public class InstructorFirebaseRepository implements InstructorRepository {
    private FirebaseDatabase firebaseDatabase;
    private List<Instructor> instructorList;

    public InstructorFirebaseRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        instructorList = new ArrayList<>();
    }

    @Override
    public void getAllInstructors(final Callback<List<Instructor>> listCallback) {
        firebaseDatabase.getReference().child("instructores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        instructorList.add(child.getValue(Instructor.class));
                    }
                    listCallback.success(instructorList);
                } catch (Exception ex) {
                    listCallback.error(ex);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    @Override
    public void getAllInstructorsAbout(String id, final Callback<List<Instructor>> listCallback) {
        firebaseDatabase.getReference().child("cursos").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                        String aux = child.child("id").getValue(String.class);
                        Query query = firebaseDatabase.getReference("instructores").orderByChild("id").equalTo(aux);
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        instructorList.add(child.getValue(Instructor.class));
                                    }
                                    if (instructorList.size() > 0) {
                                        listCallback.success(instructorList);
                                    }
                                } catch (Exception e) {
                                    listCallback.error(e);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

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
