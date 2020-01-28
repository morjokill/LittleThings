package morj.example.mongo.controller;

import morj.example.mongo.model.Book;
import morj.example.mongo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class Controller {
    BooksRepository booksRepository;

    @Autowired
    public Controller(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @PostMapping
    public void postBook(Book book) {
        booksRepository.save(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return booksRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book getBook(@PathVariable("id") String id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }
}
