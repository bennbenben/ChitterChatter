-- create schema
CREATE SCHEMA tbb_profile;
SELECT schema_name FROM information_schema.schemata;

-- create sequence
CREATE SEQUENCE tbb_profile.user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE tbb_profile.authorities_id_seq START WITH 1 INCREMENT BY 1;

-- create type
CREATE TYPE tier_type AS ENUM('red_tick', 'green_tick', 'blue_tick', 'yellow_tick');
CREATE TYPE privacy_type AS ENUM('public', 'private');
CREATE TYPE authority_role_type AS ENUM('ROLE_ADMIN', 'ROLE_USER');

CREATE TABLE tbb_profile.users(
    user_id VARCHAR(50) NOT NULL nextval('tbb_profile.user_id_seq'),
    pwd VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL,
    dob DATE NOT NULL,
    verified BOOLEAN DEFAULT FALSE,
    tier tier_type,
    privacy privacy_type DEFAULT 'public',
    avatar VARCHAR(255) NOT NULL,
    points BIGINT NOT NULL DEFAULT 0,
    created_ts TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_ts TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(user_id)
);

CREATE TABLE tbb_profile.authorities(
    id BIGINT NOT NULL nextval('tbb_profile.authorities_id_seq'),
    user_id VARCHAR(50) NOT NULL,
    authority_role authority_role_type,
    PRIMARY KEY(id),
    CONSTRAINT fk_authority_role_users
        FOREIGN KEY (user_id) REFERENCES tbb_profile.users(user_id)
);
