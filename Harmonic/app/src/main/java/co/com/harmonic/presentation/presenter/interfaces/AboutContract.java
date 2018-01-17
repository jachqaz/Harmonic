package co.com.harmonic.presentation.presenter.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.domain.model.Instrument;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public interface AboutContract {
    interface View {
        void goToAboutFragment(android.view.View view_help);

        void goToDetailFragment(android.view.View view_help);

        void refreshInstruments();

        void refreshInstructors();
    }

    interface UserActionsListener {
        //        void getAllInstructors(String id, Callback<List<Instructor>> listCallback);
        void loadAllInstruments();

        List<Instrument> getAllInstruments();

        void loadAllInstructors(String id);

        List<Instructor> getAllInstructors();
    }
}
