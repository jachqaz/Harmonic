package co.com.harmonic.presentation.presenter;

import co.com.harmonic.presentation.presenter.interfaces.DetailContract;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public class DetailPresenter implements DetailContract {
    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }
}
