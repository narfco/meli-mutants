package co.com.narfco.meli.mutants.meli.mutants;

import co.com.narfco.meli.mutants.meli.mutants.adapter.out.mongo.repository.DnaMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackageClasses = DnaMongoRepository.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
