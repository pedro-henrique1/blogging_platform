CREATE TABLE blogging
(
    id        INTEGER AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255),
    content   VARCHAR(255),
    category  VARCHAR(255),
    tags      VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP

)