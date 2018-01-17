package co.com.harmonic.domain.usecase.impl;

import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.domain.usecase.interfaces.InstructorUseCase;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.repository.impl.InstructorFirebaseRepository;
import co.com.harmonic.repository.interfaces.InstructorRepository;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public class InstructorUseCaseImpl implements InstructorUseCase {
    private InstructorRepository instructorRepository;

    public InstructorUseCaseImpl() {
        instructorRepository = new InstructorFirebaseRepository();
    }

    @Override
    public void getAllInstructors(final Callback<List<Instructor>> listCallback) {
        instructorRepository.getAllInstructors(new Callback<List<Instructor>>() {
            @Override
            public void success(List<Instructor> result) {
                listCallback.success(result);
            }

            @Override
            public void error(Exception error) {

            }
        });
    }

    @Override
    public void getAllInstructorsAbout(String id, final Callback<List<Instructor>> listCallback) {
        instructorRepository.getAllInstructorsAbout(id, new Callback<List<Instructor>>() {
            @Override
            public void success(List<Instructor> result) {
                listCallback.success(result);
            }

            @Override
            public void error(Exception error) {

            }
        });
    }
}
