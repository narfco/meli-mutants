package mutantdetector.impl;

import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.MutantDetector;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.impl.MutantDetectorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Sample.human;
import static util.Sample.mutant;

public class MutantDetectorImplTest {

    private MutantDetector mutantDetector;

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
