package morj.example.mongo.repository;

import morj.example.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Book, String> {
}
