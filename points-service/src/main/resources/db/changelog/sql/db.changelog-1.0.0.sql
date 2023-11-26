--liquibase formatted sql

--changeset author:DmitryLianguzov id:0
CREATE TABLE IF NOT EXISTS USERS(
    id BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS POINTS(
    id BIGSERIAL PRIMARY KEY,
    x BIGINT NOT NULL,
    y BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    processing_time BIGINT NOT NULL,
    creator_id BIGINT references USERS(id)
);

