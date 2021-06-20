package co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.validator.HumanDnaConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class HumanDna {

    @NotNull
    @Valid
    @HumanDnaConstraint
    public String[] dna;

}
