package spring.data.jpa.example.app;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spring.data.jpa.example.model.Book;
import spring.data.jpa.example.repository.BookRepository;

import java.util.List;

@SpringBootApplication(scanBasePackages = "spring.data.jpa.example")
@EnableJpaRepositories("spring.data.jpa.example")
@EntityScan("spring.data.jpa.example")
@Log4j
public class Application implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Autowired
    public Application(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String bookName = "Such cool book! (Not War and Peace)";

        List<Book> books = bookRepository.findByName(bookName);
        log.info("Such cool book: " + books);

        Iterable<Book> all = bookRepository.findAll();
        log.info("All books: " + all);
    }
}
