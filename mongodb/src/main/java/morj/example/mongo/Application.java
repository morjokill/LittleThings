package morj.example.mongo;

import morj.example.mongo.model.Author;
import morj.example.mongo.model.Book;
import morj.example.mongo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication(scanBasePackages = "morj.example.mongo")
@EnableMongoRepositories(basePackages = "morj.example.mongo.repository")
@EntityScan("morj.example.mongo.model")
public class Application implements CommandLineRunner {
    private BooksRepository booksRepository;

    @Autowired
    public Application(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Started!");
        List<Book> books = booksRepository.findAll();
        if (books.isEmpty()) {
            booksRepository.save(new Book("Охота на овец", "Приятная книга Мураками, написанная в более" +
                    " живом и легком стиле, чем привычная зарубежная классика," +
                    "легко читается. Гг ищет овцу.", new Author("Харуки Мураками", "Японский писатель",
                    getDateFromLocalDate(LocalDate.of(1949, Month.JANUARY, 12)), "Япония",
                    Arrays.asList("Роман", "Антиутопия", "Эссе"))));
            books = booksRepository.findAll();
        }

        System.out.println("Books: " + books);
    }

    private Date getDateFromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
