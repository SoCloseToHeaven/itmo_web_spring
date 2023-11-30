--liquibase formatted sql

--changeset author:DmitryLianguzov id:1
CREATE TABLE IF NOT EXISTS USERS(
                                    id BIGSERIAL PRIMARY KEY,
                                    username text NOT NULL UNIQUE,
                                    password text NOT NULL
);