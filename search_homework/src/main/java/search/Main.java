package search;

import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, MyStemApplicationException {
        System.out.println("Type your search request");
        Scanner sc = new Scanner(System.in);

        String next = sc.nextLine();
        String[] words;
        System.out.println("Search line: " + next);
        words = next.trim().split(" ");

        List<Set<Article>> list = new LinkedList<>();
        for (String word : words) {
            word = string_split.Main.myStemAnalyze(word);
            Set<Article> articles = Dao.getArticles(word);
            list.add(articles);
        }
        list.sort(Comparator.comparingInt(Set::size));
        for (int i = 0; i < list.size() - 1; i++) {
            for (Article article : list.get(i)) {
                if (!list.get(i + 1).contains(article)) {
                    System.out.println("No matches");
                    return;
                }
            }
        }
        System.out.println("URLS: " + list.toString());
    }
}
