package util;

import co.com.narfco.meli.mutants.meli.mutants.kernel.command.CheckHumanDna;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;

public class SampleUtil {


    public static CheckHumanDna checkHumanDna(){
        String[] dnaArray = mutant();
        return new CheckHumanDna(dnaArray);
    }

    public static String[] mutant() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return dnaArray;
    }

    public static String[] human() {
        String[] dnaArray = new String[]{
                "ATGCGA",
                "CCGTGC",
                "TTATAT",
                "AGAAGG",
                "CGCCTA",
                "TCACTG"};
        return dnaArray;
    }


    public static DnaStats dnaStats(){
        return new DnaStats(1L,2L,0.5);
    }

}
