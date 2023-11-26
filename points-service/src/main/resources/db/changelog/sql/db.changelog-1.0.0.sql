--liquibase formatted sql

--changeset author:DmitryLianguzov id:0
CREATE TABLE IF NOT EXISTS POINTS(
    id BIGSERIAL PRIMARY KEY,
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL,
    processing_time BIGINT NOT NULL,
    creator_id BIGINT
);

