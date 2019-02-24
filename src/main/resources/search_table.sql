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