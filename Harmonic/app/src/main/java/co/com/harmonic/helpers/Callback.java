package co.com.harmonic.helpers;

/**
 * Created by Rodolhan on 15/12/2017.
 */

public interface Callback<T> {
    void success(T result);

    void error(Exception error);
}
