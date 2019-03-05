package spring_jdbc_template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Book {
    private int id;
    private String name;
    private int author_id;

    public Book(String name, int author_id) {
        this.name = name;
        this.author_id = author_id;
    }
}
