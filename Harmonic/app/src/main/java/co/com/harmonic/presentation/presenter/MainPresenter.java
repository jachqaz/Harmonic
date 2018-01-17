package co.com.harmonic.presentation.presenter;

import co.com.harmonic.presentation.presenter.interfaces.MainContract;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public class MainPresenter implements MainContract.UserActionsListener {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

}
