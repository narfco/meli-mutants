package co.com.narfco.meli.mutants.meli.mutants.adapter.out;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.DnaRepositoryAdapter;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto.DnaResult;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.SampleUtil.dnaResultMutant;
import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.SampleUtil.human;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

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
        Mono<Boolean> response = this.dnaRepositoryAdapter.saveDnaRecordResult(human().getDna(), true);
        StepVerifier
                .create(response)
                .consumeNextWith(Assertions::assertTrue)
                .verifyComplete();
        verify(dnaMongoRepository, times(1))
                .save(new DnaResult(Arrays.hashCode(human().getDna()), human().getDna(), true));

    }

    @Test
    public void shouldReturnErrorWhenDnaSave() {
        when(dnaMongoRepository.save(any())).thenReturn(Mono.error(new IllegalArgumentException()));
        Mono<Boolean> response = this.dnaRepositoryAdapter.saveDnaRecordResult(human().getDna(), true);
        StepVerifier
                .create(response)
                .consumeErrorWith(t -> {
                    assertTrue(t instanceof RepositoryException);
                })
                .verify();
        verify(dnaMongoRepository, times(1))
                .save(new DnaResult(Arrays.hashCode(human().getDna()), human().getDna(), true));
    }

    @Test
    public void shouldReturnTotalCount() {
        when(dnaMongoRepository.count()).thenReturn(Mono.just(1L));
        Mono<Long> response = this.dnaRepositoryAdapter.getTotalCount();
        StepVerifier
                .create(response)
                .consumeNextWith(r -> {
                    assertEquals(r, 1L);
                })
                .verifyComplete();
        verify(dnaMongoRepository, times(1))
                .count();
    }

    @Test
    public void shouldReturnErrorWhenTotalCount() {
        when(dnaMongoRepository.count()).thenReturn(Mono.error(new IllegalArgumentException()));
        Mono<Long> response = this.dnaRepositoryAdapter.getTotalCount();
        StepVerifier
                .create(response)
                .consumeErrorWith(t -> {
                    assertTrue(t instanceof RepositoryException);
                })
                .verify();
        verify(dnaMongoRepository, times(1))
                .count();
    }

    @Test
    public void shouldReturnTotalMutantCount() {
        when(dnaMongoRepository.countByMutant(anyBoolean())).thenReturn(Mono.just(1L));
        Mono<Long> response = this.dnaRepositoryAdapter.getTotalMutantCount();
        StepVerifier
                .create(response)
                .consumeNextWith(r -> {
                    assertEquals(r, 1L);
                })
                .verifyComplete();
        verify(dnaMongoRepository, times(1))
                .countByMutant(true);
    }

    @Test
    public void shouldReturnErrorWhenTotalMutantCount() {
        when(dnaMongoRepository.countByMutant(anyBoolean())).thenReturn(Mono.error(new IllegalArgumentException()));
        Mono<Long> response = this.dnaRepositoryAdapter.getTotalMutantCount();
        StepVerifier
                .create(response)
                .consumeErrorWith(t -> {
                    assertTrue(t instanceof RepositoryException);
                })
                .verify();
        verify(dnaMongoRepository, times(1))
                .countByMutant(true);
    }


}
