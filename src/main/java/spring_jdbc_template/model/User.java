package spring_jdbc_template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
    }
}
