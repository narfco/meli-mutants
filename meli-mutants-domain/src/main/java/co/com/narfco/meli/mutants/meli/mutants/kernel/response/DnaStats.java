package co.com.narfco.meli.mutants.meli.mutants.kernel.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DnaStats {
    private Long countMutantDna;
    private Long countHumanDna;
    private double ratio;
}
