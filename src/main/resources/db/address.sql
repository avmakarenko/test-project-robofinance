--liquibase formatted sql

--changeset init:address runOnChange:true
--preconditions onFail:MARK_RAN
--preconditions-sql-check expectedResult:0 select count(1) from information_schema.tables WHERE table_schema = 'public' AND table_name = 'address'

CREATE TABLE address (
  id bigserial NOT NULL,
  country varchar(255),
  region varchar(255),
  city varchar(255),
  street varchar(255),
  house varchar(255),
  flat varchar(255),
  created timestamp,
  modified timestamp,
  CONSTRAINT pk_address PRIMARY KEY (id)
);

--rollback not required
