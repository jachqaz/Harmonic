package co.com.harmonic.presentation.presenter.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instructor;
import co.com.harmonic.domain.model.Instrument;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public interface GeneralContract {
    interface View {
        void goToAbouthFragment(android.view.View view_help);

        void goToDetailFragment(android.view.View view_help);

        void refreshInstruments();

        void refreshInstructors();
    }

    interface UserActionsListener {
        void loadAllInstruments();

        List<Instrument> getAllInstruments();

        void loadAllInstructors();

        List<Instructor> getAllInstructors();

    }
}
