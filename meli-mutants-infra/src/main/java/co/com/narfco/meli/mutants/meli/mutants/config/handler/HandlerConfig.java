package co.com.narfco.meli.mutants.meli.mutants.config.handler;

import co.com.narfco.meli.mutants.meli.mutants.adapter.in.DnaStatsHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.in.HumanDnaCheckHandler;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.DnaRepository;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.DnaRepositoryAdapter;
import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import co.com.narfco.meli.mutants.meli.mutants.service.humancheck.HumanDnaCheckHandlerImpl;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.MutantDetector;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.impl.MutantDetectorImpl;
import co.com.narfco.meli.mutants.meli.mutants.service.mutantsstats.DnaStatsHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public HumanDnaCheckHandler humanDnaCheckHandler(MutantDetector mutantDetector, DnaRepository dnaRepository){
        return new HumanDnaCheckHandlerImpl(mutantDetector, dnaRepository);
    }

    @Bean
    public DnaStatsHandler dnaStatsHandler(MutantDetector mutantDetector, DnaRepository dnaRepository){
        return new DnaStatsHandlerImpl(dnaRepository);
    }

    @Bean
    public MutantDetector mutantDetector(){
        return new MutantDetectorImpl();
    }

    @Bean
    public DnaRepository getDnaRepository(DnaMongoRepository dnaMongoRepository){
        return new DnaRepositoryAdapter(dnaMongoRepository);
    }

}
