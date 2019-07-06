--liquibase formatted sql

--changeset miliaev:UNIWORK-02-create-table-person
CREATE TABLE person
(
  id BIGSERIAL PRIMARY KEY,
  login TEXT UNIQUE NOT NULL,
  first_name TEXT NOT NULL,
  middle_name TEXT,
  last_name TEXT NOT NULL,
  birth_date DATE NOT NULL,
  education TEXT,
  organization TEXT,
  type TEXT NOT NULL
);

COMMENT ON TABLE person is 'Пользователь';
COMMENT ON COLUMN person.id is 'Идентификатор';
COMMENT ON COLUMN person.login is 'Логин';
COMMENT ON COLUMN person.first_name is 'Имя';
COMMENT ON COLUMN person.middle_name is 'Отчество';
COMMENT ON COLUMN person.last_name is 'Фамилия';
COMMENT ON COLUMN person.birth_date is 'Дата рождения';
COMMENT ON COLUMN person.education is 'Образование';
COMMENT ON COLUMN person.organization is 'Организация';
COMMENT ON COLUMN person.type is 'Тип';

--changeset miliaev:UNIWORK-02-create-table-topic
CREATE TABLE topic
(
  id BIGSERIAL PRIMARY KEY,
  type TEXT
);

COMMENT ON TABLE topic is 'Топик';
COMMENT ON COLUMN topic.id is 'Идентификатор';
COMMENT ON COLUMN topic.type is 'Тип';

--changeset miliaev:UNIWORK-02-create-table-person-topic
CREATE TABLE person_topic
(
  person_id BIGINT NOT NULL REFERENCES person,
  topic_id BIGINT NOT NULL REFERENCES topic
);

COMMENT ON TABLE person_topic is 'Таблица-связка пользователей и топиков';
COMMENT ON COLUMN person_topic.person_id is 'Идентификатор пользователя';
COMMENT ON COLUMN person_topic.topic_id is 'ТиИдентификатор топика';
