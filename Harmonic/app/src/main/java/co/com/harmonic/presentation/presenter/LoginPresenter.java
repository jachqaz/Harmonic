package co.com.harmonic.presentation.presenter;

import co.com.harmonic.domain.model.User;
import co.com.harmonic.domain.usecase.interfaces.UserUseCase;
import co.com.harmonic.domain.usecase.impl.UserUseCaseImpl;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.presentation.presenter.interfaces.LoginContract;


/**
 * Created by Rodolhan on 15/12/2017.
 */

public class LoginPresenter implements LoginContract.UserActionsListener {

    private LoginContract.View view;
    private UserUseCase userUseCase;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onLogin(String email, String password) {
        view.showProgress();
        userUseCase.login(email, password, new Callback<User>() {
            @Override
            public void success(User user) {
                view.hideProgress();
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                view.hideProgress();
                view.showMessageError(error);
            }
        });
    }

}
