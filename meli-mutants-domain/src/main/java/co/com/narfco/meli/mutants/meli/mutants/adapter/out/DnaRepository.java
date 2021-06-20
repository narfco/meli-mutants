package co.com.narfco.meli.mutants.meli.mutants.adapter.out;

import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import reactor.core.publisher.Mono;

public interface DnaRepository {

    Mono<Boolean> saveDnaRecordResult(String[] dna, boolean mutant);

    Mono<Long> getTotalCount();

    Mono<Long> getTotalMutantCount();
}
