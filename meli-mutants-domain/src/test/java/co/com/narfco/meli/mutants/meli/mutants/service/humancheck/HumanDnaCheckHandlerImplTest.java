package co.com.narfco.meli.mutants.meli.mutants.service.humancheck;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.HumanDnaCheckHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.IMutantDetector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;
import static util.SampleUtil.checkHumanDna;

public class HumanDnaCheckHandlerImplTest {

    private HumanDnaCheckHandler humanDnaCheckHandler;

    @Mock
    private IMutantDetector mutantDetector;
    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.humanDnaCheckHandler = new HumanDnaCheckHandlerImpl(mutantDetector, dnaRepository);
    }

    @Test
    void shouldReturnTrueCheckHumanDna() {
        when(mutantDetector.isMutant(any())).thenReturn(true);
        when(dnaRepository.saveDnaRecordResult(any(), anyBoolean())).thenReturn(Mono.just(true));
        Mono<Boolean> response = this.humanDnaCheckHandler.checkHumanDna(checkHumanDna());
        StepVerifier
                .create(response)
                .consumeNextWith(Assertions::assertTrue)
                .verifyComplete();
        verify(mutantDetector, times(1)).isMutant(checkHumanDna().getDnaChain());
        verify(dnaRepository, times(1)).saveDnaRecordResult(checkHumanDna().getDnaChain(), true);
    }
}
