package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.DnaStatsResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.HumanDna;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.ErrorResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.GenericResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.ResponseBuilder;
import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
                .map(r -> ResponseBuilder.ok())
                .switchIfEmpty(Mono.just(ResponseBuilder.forbidden()))
                .onErrorResume(t -> Mono.just(ResponseBuilder.generateErrorResponse(t)));
    }


    @GetMapping(value = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenericResponse>> getStats() {
        log.info("Getting mutants stats");
        return dnaStatsHandler.getHumanMutantStats()
                .map(ResponseBuilder::generateDnaStatsResponse)
                .map(ResponseBuilder::getResponseEntity);
    }


}
