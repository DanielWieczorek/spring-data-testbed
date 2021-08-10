CREATE TABLE first (
    id INT PRIMARY KEY,
    first_field VARCHAR NOT NULL
);

CREATE TABLE second (
    id INT PRIMARY KEY,
    second_field VARCHAR NOT NULL,
    fk_first INT,
    fk_second INT,
    FOREIGN KEY(fk_first) REFERENCES first(id),
    FOREIGN KEY(fk_second) REFERENCES second(id)

);

CREATE TABLE third (
    id INT PRIMARY KEY,
    third_field VARCHAR NOT NULL,
    fk_second INT NOT NULL,
    fk_first INT,
    FOREIGN KEY(fk_second) REFERENCES second(id),
    FOREIGN KEY(fk_first) REFERENCES first(id)
);