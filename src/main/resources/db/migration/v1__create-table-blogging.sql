CREATE TABLE blogging
(
    id PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(255),
    content   VARCHAR(255),
    category  VARCHAR(255),
    tags      VARCHAR(255),
    createdAt DATETIME,
    updatedAt DATETIME

)