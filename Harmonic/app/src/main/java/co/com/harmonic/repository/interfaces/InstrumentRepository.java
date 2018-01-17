package co.com.harmonic.repository.interfaces;

import java.util.List;

import co.com.harmonic.domain.model.Instrument;
import co.com.harmonic.helpers.Callback;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public interface InstrumentRepository {
    void getAllInstruments(Callback<List<Instrument>> listCallback);
}
