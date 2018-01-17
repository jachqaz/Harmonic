package co.com.harmonic.repository.impl;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.com.harmonic.domain.model.User;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.repository.interfaces.UserRepository;

/**
 * Created by juank on 15/12/2017.
 */

public class UserFirebaseRepository implements UserRepository{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public UserFirebaseRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
    }


    /**
     * Created by Rodolhan on 15/12/2017.
     */
    @Override
    public void login(String email, String password, final Callback<User> callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            databaseReference.child(firebaseUser.getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            User user = dataSnapshot.getValue(User.class);
                                            callback.success(user);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            callback.error(databaseError.toException());
                                        }
                                    });
                        } else {
                            callback.error(task.getException());
                        }
                    }
                });
    }

    @Override
    public void signUp(final User user, final Callback<User> callback) {

        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            user.setPassword(null);

                            databaseReference.child(user.getId()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                callback.success(user);
                                            } else {
                                                callback.error(task.getException());
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}



