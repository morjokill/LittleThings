package cosine_similarity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ArticleSimilarity {
    private String articleId;
    private double similarity;

    public ArticleSimilarity(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArticleSimilarity that = (ArticleSimilarity) o;
        return Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), articleId);
    }
}
