package co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.util.GenericResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DnaStatsResponse extends GenericResponse {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    private double ratio;
}
