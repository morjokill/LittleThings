package string_split;

import opennlp.tools.stemmer.snowball.SnowballStemmer;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static MyStem myStemAnalyzer = new Factory("-igd --format json")
            .newMyStem("3.0", Option.empty()).get();

    private final static String stopWordsFilePath = "src\\main\\resources\\stop_words.txt";
    private final static String regex = "[а-яА-Яё]*";

    public static void main(String[] args) throws SQLException, IOException, MyStemApplicationException {
        List<Article> articles = Dao.getArticles();
        List<String> stopWords = getStopWords();

        for (Article article : articles) {
            System.out.println(article.getContent());
            System.out.println("______________________________");

            String line = article.getContent();
            List<String> words = getWordsFromLine(line);

            words.removeAll(stopWords);

            List<String> afterPortersStem = new LinkedList<>();
            List<String> afterMyStem = new LinkedList<>();

            for (String word : words) {
                afterPortersStem.add(portersStemAnalyze(word));
                afterMyStem.add(myStemAnalyze(word));
            }

            Dao.insertIntoMyStem(article.getId(), afterMyStem);
            Dao.insertIntoPorterStem(article.getId(), afterPortersStem);
        }

    }

    private static List<String> getStopWords() throws IOException {
        return FileReader.readFile(stopWordsFilePath);
    }

    private static List<String> getWordsFromLine(String line) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        List<String> words = new LinkedList<>();
        while (matcher.find()) {
            String word = matcher.group();
            if (!"".equals(word)) {
                words.add(word.toLowerCase());
            }
        }
        return words;
    }

    private static String portersStemAnalyze(String word) {
        SnowballStemmer portersStem = new SnowballStemmer(SnowballStemmer.ALGORITHM.RUSSIAN);

        return portersStem.stem(word).toString();
    }

    private static String myStemAnalyze(String word) throws MyStemApplicationException {
        StringBuilder resultString = new StringBuilder();
        Iterable<Info> result = JavaConversions.asJavaIterable(myStemAnalyzer
                .analyze(Request.apply(word))
                .info()
                .toIterable());

        for (final Info info : result) {
            Option<String> lex = info.lex();
            if (Objects.nonNull(lex) && lex.isDefined()) {
                resultString.append(lex.get());
            }
        }

        return resultString.toString();
    }
}
