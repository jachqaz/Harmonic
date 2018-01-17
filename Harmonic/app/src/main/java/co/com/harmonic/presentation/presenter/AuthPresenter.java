package co.com.harmonic.presentation.presenter;

import co.com.harmonic.presentation.presenter.interfaces.AuthContract;

/**
 * Created by Rodolhan on 15/12/2017.
 */

public class AuthPresenter implements AuthContract.UserActionsLister {
    private AuthContract.View view;

    public AuthPresenter(AuthContract.View view) {
        this.view = view;
    }

    @Override
    public void goToFirstFragment() {
        view.goToLoginFragment();
    }
}
