package co.com.harmonic.repository.interfaces;

import co.com.harmonic.domain.model.User;
import co.com.harmonic.helpers.Callback;

/**
 * Created by juank on 15/12/2017.
 */

public interface UserRepository {

    void login(String email, String password, Callback<User> callback);

    void signUp(User user, Callback<User> callback);
}
