CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE child_item(
    id SERIAL PRIMARY KEY,
    parent_item INT,
    name VARCHAR(255),
    FOREIGN KEY (parent_item) REFERENCES item(id)
);

CREATE TABLE child_child_item(
    id SERIAL PRIMARY KEY,
    child_item_id INT,
    name VARCHAR(255),
    lang VARCHAR(2),
    FOREIGN KEY (child_item_id) REFERENCES child_item(id),
    UNIQUE(child_item_id, lang)
);

