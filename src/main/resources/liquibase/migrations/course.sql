--liquibase formatted sql

--changeset miliaev:UNIWORK-03-create-table-course
CREATE TABLE course
(
  id BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  person_id BIGINT NOT NULL REFERENCES person
);

COMMENT ON TABLE course is 'Курс';
COMMENT ON COLUMN course.id is 'Идентификатор';
COMMENT ON COLUMN course.name is 'Название';
COMMENT ON COLUMN course.person_id is 'Создатель курса'

--changeset miliaev:UNIWORK-03-create-table-course-topic
CREATE TABLE course_topic
(
  course_id BIGINT NOT NULL REFERENCES course,
  topic_id BIGINT NOT NULL REFERENCES topic
);

COMMENT ON TABLE course_topic is 'Таблица-связка курсов и топиков';
COMMENT ON COLUMN course_topic.course_id is 'Идентификатор курса';
COMMENT ON COLUMN course_topic.topic_id is 'Идентификатор топика';
