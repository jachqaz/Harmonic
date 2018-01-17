package co.com.harmonic.domain.usecase.impl;

import java.util.List;

import co.com.harmonic.domain.model.Instrument;
import co.com.harmonic.domain.usecase.interfaces.InstrumentUseCase;
import co.com.harmonic.helpers.Callback;
import co.com.harmonic.repository.impl.InstrumentFirebaseRepository;
import co.com.harmonic.repository.interfaces.InstrumentRepository;

/**
 * Created by Rodolhan on 18/12/2017.
 */

public class InstrumentUseCaseImpl implements InstrumentUseCase {
    private InstrumentRepository instrumentRepository;

    public InstrumentUseCaseImpl() {
        instrumentRepository = new InstrumentFirebaseRepository();
    }

    @Override
    public void getAllInstruments(final Callback<List<Instrument>> listCallback) {
        instrumentRepository.getAllInstruments(new Callback<List<Instrument>>() {
            @Override
            public void success(List<Instrument> result) {
                listCallback.success(result);
            }

            @Override
            public void error(Exception error) {

            }
        });
    }
}
