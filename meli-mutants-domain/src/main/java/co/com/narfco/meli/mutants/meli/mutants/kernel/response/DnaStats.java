package co.com.narfco.meli.mutants.meli.mutants.kernel.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class DnaStats {
    private final Long countMutantDna;
    private final Long countHumanDna;
    private final double ratio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnaStats dnaStats = (DnaStats) o;
        return Double.compare(dnaStats.ratio, ratio) == 0 && Objects.equals(countMutantDna, dnaStats.countMutantDna) && Objects.equals(countHumanDna, dnaStats.countHumanDna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countMutantDna, countHumanDna, ratio);
    }
}
