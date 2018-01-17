package co.com.harmonic.presentation.presenter.interfaces;

/**
 * Created by Rodolhan on 15/12/2017.
 */

public interface AuthContract {

    interface View {
        void goToLoginFragment();

        void goMainActivity();
    }

    interface UserActionsLister {
        void goToFirstFragment();
    }
}
