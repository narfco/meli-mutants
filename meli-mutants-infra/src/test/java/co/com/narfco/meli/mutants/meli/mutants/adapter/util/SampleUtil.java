package co.com.narfco.meli.mutants.meli.mutants.adapter.util;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.dto.HumanDna;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto.DnaResult;

public class SampleUtil {


    public static HumanDna human() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return new HumanDna(dnaArray);
    }

    public static HumanDna mutant() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return new HumanDna(dnaArray);
    }

    public static HumanDna invalidDna() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTAGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return new HumanDna(dnaArray);
    }

    public static HumanDna invalidDnaLetter() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTAGTE",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return new HumanDna(dnaArray);
    }

    public static DnaResult dnaResultMutant(){
        return new DnaResult(1, mutant().getDna(),true);
    }
}
