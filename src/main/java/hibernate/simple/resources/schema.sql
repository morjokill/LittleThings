DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE books (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(255),
  author_id INT REFERENCES users (id)
);

CREATE TABLE users (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(255)
);