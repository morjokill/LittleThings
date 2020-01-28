package morj.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String name;
    private String description;
    private Author author;

    public Book(String name, String description, Author author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }
}
