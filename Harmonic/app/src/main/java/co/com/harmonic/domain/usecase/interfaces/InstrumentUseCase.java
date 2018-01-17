package co.com.harmonic.domain.usecase.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instrument;
import co.com.harmonic.helpers.Callback;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public interface InstrumentUseCase {
    void getAllInstruments(Callback<List<Instrument>> listCallback);
}
