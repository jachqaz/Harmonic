package co.com.harmonic.repository.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.helpers.Callback;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public interface InstructorRepository {
    void getAllInstructors(Callback<List<Instructor>> listCallback);

    void getAllInstructorsAbout(String id, Callback<List<Instructor>> listCallback);
}
