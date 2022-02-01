
package Book_Exchange.Books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookInitializer {


    private static final Logger log = LoggerFactory.getLogger(BookInitializer.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {

        return args -> {
            log.info("Initializing the first books in the library...");
            repository.save(new Book("The Da Vinci Code", "9780552154017", "Dan Brown",100));
            repository.save(new Book("Sophie's world", "9780425152256","Jostein Gaarder",100));
            repository.save(new Book("Inferno", "1490309780", "Dante Alighieri",100));
            repository.save(new Book("For every solution, a problem", "3404156145", "Kerstin Gier",100));
            repository.save(new Book("Harry Potter and the order of the phoenix", "0747569401", "J. K. Rowling",100));
            repository.save(new Book("Harry Potter and the philosopher's stone", "9780747532743", "J. K. Rowling", 100 ));
            repository.save(new Book("I am the gate", "9783893380886", "Osho", 100));
            repository.save(new Book("The shadow of the wind", "9780143034902", "Carlos Ruiz Zafon",100));
            repository.save(new Book("Demian", "9783518188163", "Hermann Hesse", 100));
            repository.save(new Book("The antediluvian world - Atlantis", "0800759233717", "Ignatius L. Donnelly", 100));
            repository.save(new Book("Madame Curie" , "9783596222438", "Eve Curie", 100));
        };

    }

}
