package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import reactor.core.publisher.Mono;

public interface HumanDnaCheckHandler {

    Mono<Boolean> checkHumanDna(CheckHumanDna humanDna);

}
