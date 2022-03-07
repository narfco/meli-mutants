package mutantdetector.impl;

import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.IMutantDetector;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.impl.MutantDetectorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.SampleUtil.human;
import static util.SampleUtil.mutant;

public class MutantDetectorImplTest {

    private IMutantDetector mutantDetector;

    @BeforeEach
    public void setUp(){
        mutantDetector = new MutantDetectorImpl();
    }

    @Test
    public void shouldReturnFalse() {
        assertFalse(this.mutantDetector.isMutant(human()));
    }

    @Test
    public void shouldReturnTrue() {
        assertTrue(this.mutantDetector.isMutant(mutant()));
    }
}
