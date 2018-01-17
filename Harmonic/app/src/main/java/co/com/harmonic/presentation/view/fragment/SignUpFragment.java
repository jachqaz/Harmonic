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
import co.com.harmonic.presentation.presenter.SignUpPresenter;
import co.com.harmonic.presentation.presenter.interfaces.SignUpContract;
import co.com.harmonic.presentation.view.activity.AuthActivity;
import co.com.harmonic.presentation.view.activity.MainActivity;


public class SignUpFragment extends Fragment implements SignUpContract.View, View.OnClickListener{

    private SignUpContract.UserActionsListener userActionsListener;
    private TextInputLayout til_FullName;
    private TextInputLayout til_Correo;
    private TextInputLayout til_Password;
    private Button btn_registrar;
    private Button btn_cancelar;
    private ProgressBar progressBar;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment getInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        userActionsListener = new SignUpPresenter(this);

        til_FullName = view.findViewById(R.id.til_FullName);
        til_Correo = view.findViewById(R.id.til_correo);
        til_Password = view.findViewById(R.id.til_password);
        btn_registrar = view.findViewById(R.id.btn_registrar);
        btn_cancelar = view.findViewById(R.id.btn_cancelar);
        progressBar = view.findViewById(R.id.pb_sign_up);

        btn_registrar.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_registrar:
                onSignUp();
                break;
            case R.id.btn_cancelar:
                goToLoginFragment();
                break;
        }
    }

    private void onSignUp() {
        try{
            boolean result = true;
            String fullname = til_FullName.getEditText().getText().toString();
            String email = til_Correo.getEditText().getText().toString();
            String password = til_Password.getEditText().getText().toString();

            if(Utilities.isEmpty(email)){
                til_Correo.setError(getString(R.string.is_required));
                til_Correo.setErrorEnabled(true);
                result = false;
            }else{
                til_Correo.setError(null);
                til_Correo.setErrorEnabled(false);
            }

            if(Utilities.isEmpty(password)){
                til_Password.setError(getString(R.string.is_required));
                til_Password.setErrorEnabled(true);
                result = false;
            }else{
                til_Password.setError(null);
                til_Password.setErrorEnabled(false);
            }

            if(result){
                userActionsListener.onSignUp(fullname,email,password);
            }

        }catch (Exception e){

        }
    }

    @Override
    public void goToLoginFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(LoginFragment.getInstance(), true);
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
        progressBar.setVisibility(View.VISIBLE);
        btn_cancelar.setEnabled(false);
        btn_registrar.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        btn_cancelar.setEnabled(true);
        btn_registrar.setEnabled(true);
    }
}
