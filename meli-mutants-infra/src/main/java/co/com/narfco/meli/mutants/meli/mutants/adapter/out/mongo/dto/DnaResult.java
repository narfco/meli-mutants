package co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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
}
