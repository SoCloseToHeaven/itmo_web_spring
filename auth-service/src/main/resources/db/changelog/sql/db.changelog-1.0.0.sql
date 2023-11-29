--liquibase formatted sql

--changeset author:DmitryLianguzov id:0
CREATE TABLE IF NOT EXISTS USERS(
    id BIGSERIAL PRIMARY KEY,
    username text NOT NULL,
    password text NOT NULL
);

