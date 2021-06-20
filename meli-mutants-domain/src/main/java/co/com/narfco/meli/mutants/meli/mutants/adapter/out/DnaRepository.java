package co.com.narfco.meli.mutants.meli.mutants.adapter.out;

import reactor.core.publisher.Mono;

public interface DnaRepository {

    Mono<Boolean> saveDnaRecordResult(String[] dna, boolean mutant);

    Mono<Long> getTotalCount();

    Mono<Long> getTotalMutantCount();
}
