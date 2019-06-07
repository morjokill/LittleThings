package little.things.compare_to;

import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@ToString
public class User implements Comparable<User> {
    private String name;
    private String surname;
    private int age;

    @Override
    public int compareTo(User that) {
        return ComparisonChain.start()
                .compare(this.age, that.age)
                .result();
    }

    public static void main(String[] args) {
        User firstUser = new User("Maxim", "Romanov", 22);
        User secondUser = new User("Maxim", "Romanov", 23);
        User thirdUser = new User("Ne Maxim", "Ne Romanov", -22);

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser);
        users.sort(User::compareTo);
        System.out.println(users);
    }
}
