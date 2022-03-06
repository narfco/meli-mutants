package co.com.narfco.meli.mutants.meli.mutants.adapter.in.util;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.DnaStatsResponse;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilderHelper {

    public static ResponseEntity<GenericResponse> getResponseEntity(DnaStatsResponse stats) {
        return ResponseEntity.ok(stats);
    }

    public static DnaStatsResponse generateDnaStatsResponse(DnaStats dnaStats) {
        return new DnaStatsResponse(dnaStats.getCountMutantDna(), dnaStats.getCountHumanDna(), dnaStats.getRatio());
    }

    public static ResponseEntity<GenericResponse> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public static ResponseEntity<GenericResponse> ok() {
        return ResponseEntity.ok().build();
    }

    public static ResponseEntity<GenericResponse> generateErrorResponse(Throwable t) {
        return new ResponseEntity<GenericResponse>(new ErrorResponse(t.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
