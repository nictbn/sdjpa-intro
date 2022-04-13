package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Long count = bookRepository.count();

        if (count > 0) {
            log.debug("Database already initialized, skipping initialization");
            return;
        }

        log.debug("Initializing database");

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");

        System.out.println("Id: " + bookDDD.getId());

        Book savedDDD = bookRepository.save(bookDDD);

        System.out.println("Id: " + savedDDD.getId());

        Book bookSIA = new Book("Spring In Action", "234235", "O'Reily");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });
    }
}
