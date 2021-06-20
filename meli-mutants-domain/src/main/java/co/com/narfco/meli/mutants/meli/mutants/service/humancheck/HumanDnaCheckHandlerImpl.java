package co.com.narfco.meli.mutants.meli.mutants.service.humancheck;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.HumanDnaCheckHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.MutantDetector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class HumanDnaCheckHandlerImpl implements HumanDnaCheckHandler {

    private final MutantDetector mutantDetector;
    private final DnaRepository dnaRepository;

    @Override
    public Mono<Boolean> checkHumanDna(CheckHumanDna humanDna) {

        return Mono.just(mutantDetector.isMutant(humanDna.getDnaChain()))
                .doOnError(t -> log.error("Error analyzing dna" + t.getMessage()))
                .flatMap(r -> dnaRepository.saveDnaRecordResult(humanDna.getDnaChain(), r));

    }
}
