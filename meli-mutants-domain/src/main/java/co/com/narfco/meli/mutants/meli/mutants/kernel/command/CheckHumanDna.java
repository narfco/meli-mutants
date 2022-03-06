package co.com.narfco.meli.mutants.meli.mutants.kernel.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class CheckHumanDna {
    private String[] dnaChain;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckHumanDna that = (CheckHumanDna) o;
        return Arrays.equals(dnaChain, that.dnaChain);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dnaChain);
    }
}
