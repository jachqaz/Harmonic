package co.com.harmonic.presentation.presenter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.domain.model.Instrument;
import co.com.harmonic.domain.usecase.impl.InstructorUseCaseImpl;
import co.com.harmonic.domain.usecase.impl.InstrumentUseCaseImpl;
import co.com.harmonic.domain.usecase.interfaces.InstructorUseCase;
import co.com.harmonic.domain.usecase.interfaces.InstrumentUseCase;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.presentation.presenter.interfaces.AboutContract;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public class AboutPresenter implements AboutContract.UserActionsListener {
    View view_help;
    private AboutContract.View view;
    private InstrumentUseCase instrumentUseCase;
    private InstructorUseCase instructorUseCase;
    private List<Instrument> instrumentList;
    private List<Instructor> instructorList;
    public AboutPresenter(AboutContract.View view) {
        this.view = view;
        this.instrumentUseCase = new InstrumentUseCaseImpl();
        this.instructorUseCase = new InstructorUseCaseImpl();
        this.instrumentList = new ArrayList<>(0);
        this.instructorList = new ArrayList<>(0);
    }


    @Override
    public void loadAllInstruments() {
        instrumentUseCase.getAllInstruments(new Callback<List<Instrument>>() {
            @Override
            public void success(List<Instrument> result) {
                instrumentList.clear();
                if (result != null) {
                    instrumentList.addAll(result);
                    view.refreshInstruments();
                }
            }

            @Override
            public void error(Exception error) {

            }
        });
    }

    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentList;
    }

    @Override
    public void loadAllInstructors(String id) {
        instructorUseCase.getAllInstructorsAbout(id, new Callback<List<Instructor>>() {
            @Override
            public void success(List<Instructor> result) {
                instructorList.clear();
                if (result != null) {
                    instructorList.addAll(result);
                    view.refreshInstructors();
                }
            }

            @Override
            public void error(Exception error) {

            }
        });
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorList;
    }

//    @Override
//    public void getAllInstructors(String id, final Callback<List<Instructor>> listCallback) {
//        instructorUseCase.getAllInstructorsAbout(id, new Callback<List<Instructor>>() {
//            @Override
//            public void success(List<Instructor> result) {
//                listCallback.success(result);
//            }
//
//            @Override
//            public void error(Exception error) {
//
//            }
//        });
//    }
}
