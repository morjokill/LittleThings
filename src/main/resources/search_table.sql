CREATE TABLE articles(
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title character varying(255),
  keywords character varying(255),
  content text,
  url character varying(255),
  student_id integer,
);

CREATE TABLE words_mystem (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
term VARCHAR(64),
articles_id UUID REFERENCES articles (id)
);

CREATE TABLE words_porter (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
term VARCHAR(64),
articles_id UUID REFERENCES articles (id)
);

CREATE TABLE terms_list (
  term_id   UUID REFERENCES words_mystem (id) PRIMARY KEY,
  term_text VARCHAR(255) UNIQUE
);

CREATE TABLE article_term (
  article_id UUID,
  term_id    UUID REFERENCES terms_list (term_id)
);


INSERT INTO terms_list SELECT DISTINCT ON (term)
                         id   term_id,
                         term term_text
                       FROM words_mystem;

INSERT INTO article_term SELECT
  m2.articles_id article_id,
  tl.term_id term_id
FROM terms_list tl INNER JOIN words_mystem m2 on tl.term_text = m2.term;