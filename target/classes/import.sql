-- DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id INTEGER, name VARCHAR(255), password VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (id, name, password) VALUES (1, 'admin', 'admin123');
INSERT INTO users (id, name, password) VALUES (2, 'user', 'user123');

-- DROP TABLE statuses IF EXISTS;
CREATE TABLE IF NOT EXISTS statuses (id INTEGER, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO statuses (id, name) VALUES (1, 'new');
INSERT INTO statuses (id, name) VALUES (2, 'in progress');
INSERT INTO statuses (id, name) VALUES (3, 'closed');

-- DROP TABLE tasks IF EXISTS;
CREATE TABLE IF NOT EXISTS tasks (id BIGSERIAL, title VARCHAR(255), id_owner INTEGER, id_executer INTEGER, description VARCHAR(1000), id_status INTEGER, PRIMARY KEY (id));