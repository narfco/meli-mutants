package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.HumanDna;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.GenericResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.ResponseBuilderHelper;
import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MutantsAdapter {

    private final HumanDnaCheckHandler humanDnaCheckHandler;
    private final DnaStatsHandler dnaStatsHandler;

    @PostMapping(value = "mutant", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenericResponse>> verifyMutant(@RequestBody @Valid final HumanDna humanDna) {
        log.info("Verifying human DNA");
        return humanDnaCheckHandler.checkHumanDna(new CheckHumanDna(humanDna.getDna()))
                .filter(r -> r)
                .map(r -> ResponseBuilderHelper.ok())
                .switchIfEmpty(Mono.just(ResponseBuilderHelper.forbidden()))
                .onErrorResume(t -> Mono.just(ResponseBuilderHelper.generateErrorResponse(t)));
    }


    @GetMapping(value = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenericResponse>> getStats() {
        log.info("Getting mutants stats");
        return dnaStatsHandler.getHumanMutantStats()
                .map(ResponseBuilderHelper::generateDnaStatsResponse)
                .map(ResponseBuilderHelper::getResponseEntity);
    }


}
