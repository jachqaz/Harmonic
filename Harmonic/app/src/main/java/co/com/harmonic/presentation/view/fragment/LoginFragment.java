package co.com.harmonic.presentation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import co.com.harmonic.R;
import co.com.harmonic.helpers.Utilities;
import co.com.harmonic.presentation.presenter.LoginPresenter;
import co.com.harmonic.presentation.presenter.interfaces.LoginContract;
import co.com.harmonic.presentation.view.activity.AuthActivity;
import co.com.harmonic.presentation.view.activity.MainActivity;


public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.UserActionsListener mActionsListener;
    private Button btnSignIn;
    private Button btnSignUp;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private ProgressBar pbProgress;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActionsListener = new LoginPresenter(this);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        pbProgress = view.findViewById(R.id.pbProgress);


        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                onLogin();
                break;
            case R.id.btnSignUp:
                goToSignUpFragment();
                break;
        }
    }

    private void onLogin() {
        boolean result = true;
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();

        if (Utilities.isEmpty(email)) {
            tilEmail.setError(getString(R.string.is_required));
            tilEmail.setErrorEnabled(true);
            result = false;
        } else {
            tilEmail.setError(null);
            tilEmail.setErrorEnabled(false);
        }

        if (Utilities.isEmpty(password)) {
            tilPassword.setError(getString(R.string.is_required));
            tilPassword.setErrorEnabled(true);
            result = false;
        } else {
            tilPassword.setError(null);
            tilPassword.setErrorEnabled(false);
        }

        //Si la validaciones no generaron errores
        if (result) {
            mActionsListener.onLogin(email, password);
        }
    }

    public void goToSignUpFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(SignUpFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
        Log.e("Error", String.valueOf(error));
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
        btnSignIn.setEnabled(false);
        btnSignUp.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.INVISIBLE);
        btnSignIn.setEnabled(true);
        btnSignUp.setEnabled(true);
    }


}