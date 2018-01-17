package co.com.harmonic.domain.usecase.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.helpers.Callback;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public interface InstructorUseCase {
    void getAllInstructors(Callback<List<Instructor>> listCallback);

    void getAllInstructorsAbout(String id, Callback<List<Instructor>> listCallback);
}
