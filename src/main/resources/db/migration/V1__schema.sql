CREATE TABLE IF NOT EXISTS author
(
    id          BIGINT          NOT NULL    AUTO_INCREMENT  UNIQUE  PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL
);

CREATE TABLE IF NOT EXISTS to_do
(
    id          BIGINT          NOT NULL    AUTO_INCREMENT  UNIQUE  PRIMARY KEY,
    title       VARCHAR(255)    NOT NULL,
    completed   BOOLEAN         NOT NULL,
    author_id   BIGINT          NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);
