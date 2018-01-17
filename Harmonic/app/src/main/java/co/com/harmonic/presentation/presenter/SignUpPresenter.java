package co.com.harmonic.presentation.presenter;

import co.com.harmonic.domain.model.User;
import co.com.harmonic.domain.usecase.impl.UserUseCaseImpl;
import co.com.harmonic.domain.usecase.interfaces.UserUseCase;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.presentation.presenter.interfaces.SignUpContract;


/**
 * Created by Rodolhan on 15/12/2017.
 */

public class SignUpPresenter implements SignUpContract.UserActionsListener {

    private SignUpContract.View view;
    private UserUseCase userUseCase;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onSignUp(String fullName, String email, String password) {

        view.showProgress();

        userUseCase.signUp(fullName, email, password, new Callback<User>() {
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
