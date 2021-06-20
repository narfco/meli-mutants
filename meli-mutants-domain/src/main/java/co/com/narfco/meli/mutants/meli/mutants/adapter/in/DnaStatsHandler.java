package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import reactor.core.publisher.Mono;

public interface DnaStatsHandler {

    Mono<DnaStats> getHumanMutantStats();

}
