package co.com.narfco.meli.mutants.meli.mutants.adapter.in;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import co.com.narfco.meli.mutants.meli.mutants.kernel.response.DnaStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.human;
import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.invalidDna;
import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.invalidDnaLetter;
import static co.com.narfco.meli.mutants.meli.mutants.adapter.util.Sample.mutant;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(MutantsAdapter.class)
public class MutantsAdapterTest {

    private final String expectedStats = "{\"ratio\":0.6666666666666666,\"count_mutant_dna\":2,\"count_human_dna\":3}";

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private HumanDnaCheckHandler humanDnaCheckHandler;
    @MockBean
    private DnaStatsHandler dnaStatsHandler;
    @MockBean
    private DnaMongoRepository dnaMongoRepository;


    @Test
    public void shouldOkIsMutant() throws Exception {
        when(humanDnaCheckHandler.checkHumanDna(any())).thenReturn(Mono.just(true));
        webTestClient
                .post()
                .uri("/mutant")
                .body(BodyInserters.fromValue(mutant()))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void shouldForbiddenIsMutant() throws Exception {
        when(humanDnaCheckHandler.checkHumanDna(any())).thenReturn(Mono.just(false));
        webTestClient
                .post()
                .uri("/mutant")
                .body(BodyInserters.fromValue(human()))
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    public void shouldErrorMutant() throws Exception {
        when(humanDnaCheckHandler.checkHumanDna(any())).thenReturn(Mono.error(new RuntimeException("", null)));
        webTestClient
                .post()
                .uri("/mutant")
                .body(BodyInserters.fromValue(mutant()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldBadRequestMutant() throws Exception {
        when(humanDnaCheckHandler.checkHumanDna(any())).thenReturn(Mono.error(new RuntimeException("", null)));
        webTestClient
                .post()
                .uri("/mutant")
                .body(BodyInserters.fromValue(invalidDna()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldBadRequestLetterMutant() throws Exception {
        when(humanDnaCheckHandler.checkHumanDna(any())).thenReturn(Mono.error(new RuntimeException("", null)));
        webTestClient
                .post()
                .uri("/mutant")
                .body(BodyInserters.fromValue(invalidDnaLetter()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldOkStats() throws Exception {
        when(dnaStatsHandler.getHumanMutantStats())
                .thenReturn(Mono.just(new DnaStats(2L, 3L, 0.6666666666666666)));
        webTestClient
                .get()
                .uri("/stats")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody().json(expectedStats);
    }

    @Test
    public void shouldErrorStats() throws Exception {
        when(dnaStatsHandler.getHumanMutantStats())
                .thenReturn(Mono.error(new RuntimeException("", null)));
        webTestClient
                .get()
                .uri("/stats")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
