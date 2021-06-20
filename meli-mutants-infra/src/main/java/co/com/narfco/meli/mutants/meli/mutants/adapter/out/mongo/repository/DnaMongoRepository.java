package co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.dto.DnaResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DnaMongoRepository extends ReactiveCrudRepository<DnaResult, UUID> {

    Mono<Long> countByMutant(boolean mutant);
}
