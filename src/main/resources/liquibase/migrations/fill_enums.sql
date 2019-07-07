--liquibase formatted sql

--changeset miliaev:UNIWORK-04-fill-table-topic
INSERT INTO topic(name) VALUES ('PROGRAMMING'), ('MATH'), ('PHYSICS'), ('BIOLOGY'), ('BUSINESS'), ('ENGLISH');
