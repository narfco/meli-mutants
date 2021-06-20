package co.com.narfco.meli.mutants.meli.mutants.service.mutantsstats;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.DnaStatsHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static util.Sample.dnaStats;

public class DnaStatsHandlerImplTest {


    private DnaStatsHandler dnaStatsHandler;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.dnaStatsHandler = new DnaStatsHandlerImpl(dnaRepository);
    }

    @Test
    void shouldReturnTrueCheckHumanDna() {
        when(dnaRepository.getTotalCount()).thenReturn(Mono.just(2L));
        when(dnaRepository.getTotalMutantCount()).thenReturn(Mono.just(1L));
        Mono<DnaStats> response = this.dnaStatsHandler.getHumanMutantStats();
        StepVerifier
                .create(response)
                .expectNext(dnaStats())
                .verifyComplete();
    }
}
