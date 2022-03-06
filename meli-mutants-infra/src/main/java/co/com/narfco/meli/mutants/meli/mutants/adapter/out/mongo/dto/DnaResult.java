package co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Document(collection = "dna_stats")
public class DnaResult {
    @Id
    @Indexed
    private int id;
    private String[] chain;
    @Indexed
    private boolean mutant;

    public DnaResult(int id, String[] chain,  boolean mutant) {
        this.id = id;
        this.chain = chain;
        this.mutant = mutant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnaResult dnaResult = (DnaResult) o;
        return id == dnaResult.id && mutant == dnaResult.mutant && Arrays.equals(chain, dnaResult.chain);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, mutant);
        result = 31 * result + Arrays.hashCode(chain);
        return result;
    }
}
