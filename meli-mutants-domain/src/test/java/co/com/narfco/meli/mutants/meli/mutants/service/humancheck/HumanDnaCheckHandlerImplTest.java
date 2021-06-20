package co.com.narfco.meli.mutants.meli.mutants.service.humancheck;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.HumanDnaCheckHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.MutantDetector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static util.Sample.checkHumanDna;

public class HumanDnaCheckHandlerImplTest {

    private HumanDnaCheckHandler humanDnaCheckHandler;

    @Mock
    private MutantDetector mutantDetector;
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
                .expectNext(true)
                .verifyComplete();
    }
}
