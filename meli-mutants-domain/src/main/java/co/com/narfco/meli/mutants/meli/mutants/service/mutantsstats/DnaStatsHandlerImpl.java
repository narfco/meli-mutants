package co.com.narfco.meli.mutants.meli.mutants.service.mutantsstats;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.DnaStatsHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DnaStatsHandlerImpl implements DnaStatsHandler {

    private final DnaRepository dnaRepository;

    @Override
    public Mono<DnaStats> getHumanMutantStats() {
        return dnaRepository.getTotalCount()
                .zipWith(dnaRepository.getTotalMutantCount())
                .map(tuple -> new DnaStats(tuple.getT2(), tuple.getT1(),
                        ((double) tuple.getT2() / (double) tuple.getT1())));
    }
}
