CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isBorrowed BOOLEAN NOT NULL DEFAULT FALSE,
    dueDate DATE NULL
);