package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.DnaStatsResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.HumanDna;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.ErrorResponse;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.GenericResponse;
import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
                .map(r -> ok())
                .switchIfEmpty(Mono.just(forbidden()))
                .onErrorResume(t -> Mono.just(generateErrorResponse(t)));
    }


    @GetMapping(value = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenericResponse>> getStats() {
        log.info("Getting mutants stats");
        return dnaStatsHandler.getHumanMutantStats()
                .map(this::generateDnaStatsResponse)
                .map(this::getResponseEntity);
    }

    private ResponseEntity<GenericResponse> getResponseEntity(DnaStatsResponse stats) {
        return ResponseEntity.ok(stats);
    }

    private DnaStatsResponse generateDnaStatsResponse(DnaStats dnaStats) {
        return new DnaStatsResponse(dnaStats.getCountMutantDna(), dnaStats.getCountHumanDna(), dnaStats.getRatio());
    }

    private ResponseEntity<GenericResponse> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private ResponseEntity<GenericResponse> ok() {
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<GenericResponse> generateErrorResponse(Throwable t) {
        return new ResponseEntity<GenericResponse>(new ErrorResponse(t.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
