package co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto.DnaResult;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class DnaRepositoryAdapter implements DnaRepository {

    private final DnaMongoRepository dnaMongoRepository;

    @Override
    public Mono<Boolean> saveDnaRecordResult(String[] dna, boolean mutant) {

        return dnaMongoRepository.save(getEntity(dna, mutant))
                .map(dnaResult -> mutant)
                .doOnError(t -> log.error("Error saving results" + t.getMessage()))
                .onErrorResume(t -> Mono.error(
                        new RepositoryException("Error saving results " + t.getLocalizedMessage(), t)));

    }

    @Override
    public Mono<Long> getTotalCount() {
        return dnaMongoRepository.count()
                .doOnError(t -> log.error("Error getting total results" + t.getMessage()))
                .onErrorResume(t -> Mono.error(
                        new RepositoryException("Error getting total results " + t.getLocalizedMessage(), t)));
    }

    @Override
    public Mono<Long> getTotalMutantCount() {
        return dnaMongoRepository.countByMutant(true)
                .doOnError(t -> log.error("Error getting total mutant results" + t.getMessage()))
                .onErrorResume(t -> Mono.error(
                        new RepositoryException("Error getting total mutant results " + t.getLocalizedMessage(), t)));
    }


    private DnaResult getEntity(String[] dna, boolean mutant) {
        return new DnaResult(Arrays.hashCode(dna), dna, mutant);
    }

}
