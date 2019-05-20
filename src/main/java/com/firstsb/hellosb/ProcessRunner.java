package com.firstsb.hellosb;
import com.firstsb.hellosb.persistence.model.Cosa;
import com.firstsb.hellosb.persistence.repository.CosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProcessRunner implements CommandLineRunner {

    @Autowired
    private CosaRepository cosaRepository;

    @Override
    public void run(String... args) throws Exception {

        cosaRepository.deleteAll();

        // save a couple of customers
        cosaRepository.save(new Cosa("Alice", "Smith"));
        cosaRepository.save(new Cosa("Bob", "Smith"));

        // fetch all customers
        System.out.println("Cosas found with findAll():");
        System.out.println("-------------------------------");
        for (Cosa cosa : cosaRepository.findAll()) {
            System.out.println(cosa);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Cosa found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        for (Cosa cosa : cosaRepository.findByNombre("Alice")) {
            System.out.println(cosa);
        }

        System.out.println("********************************");

        System.out.println("Cosas found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Cosa cosa : cosaRepository.findByDescripcion("Smith")) {
            System.out.println(cosa);
        }
    }

}

