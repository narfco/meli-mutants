package co.com.narfco.meli.mutants.meli.mutants.adapter.out;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.DnaRepositoryAdapter;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.dnaResultMutant;
import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.human;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DnaRepositoryAdapterTest {

    private DnaRepositoryAdapter dnaRepositoryAdapter;
    @Mock
    private DnaMongoRepository dnaMongoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.dnaRepositoryAdapter = new DnaRepositoryAdapter(dnaMongoRepository);
    }

    @Test
    public void shouldReturnDnaSaved() {
        when(dnaMongoRepository.save(any())).thenReturn(Mono.just(dnaResultMutant()));
        Mono<Boolean> response =  this.dnaRepositoryAdapter.saveDnaRecordResult(human().getDna(),true);
        StepVerifier
                .create(response)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    public void shouldReturnErrorWhenDnaSave() {
        when(dnaMongoRepository.save(any())).thenReturn(Mono.error(new IllegalArgumentException()));
        Mono<Boolean> response =  this.dnaRepositoryAdapter.saveDnaRecordResult(human().getDna(),true);
        StepVerifier
                .create(response)
                .expectError(RepositoryException.class)
                .verify();
    }

    @Test
    public void shouldReturnTotalCount() {
        when(dnaMongoRepository.count()).thenReturn(Mono.just(1L));
        Mono<Long> response =  this.dnaRepositoryAdapter.getTotalCount();
        StepVerifier
                .create(response)
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    public void shouldReturnErrorWhenTotalCount() {
        when(dnaMongoRepository.count()).thenReturn(Mono.error(new IllegalArgumentException()));
        Mono<Long> response =  this.dnaRepositoryAdapter.getTotalCount();
        StepVerifier
                .create(response)
                .expectError(RepositoryException.class)
                .verify();
    }

}
