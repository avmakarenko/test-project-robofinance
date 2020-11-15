--liquibase formatted sql

--changeset init:address runOnChange:true
--preconditions onFail:MARK_RAN
--preconditions-sql-check expectedResult:0 select count(1) from information_schema.tables WHERE table_schema = 'public' AND table_name = 'customer'

CREATE TABLE customer (
    id bigserial NOT NULL,
    registered_address_id bigint NOT NULL,
    actual_address_id bigint NOT NULL,
    first_name varchar(255) NULL,
    last_name varchar(255) NULL,
    middle_name varchar(255) NULL,
    sex varchar(6) NOT NULL,
    CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY ((ARRAY['male'::character varying, 'female'::character varying])::text[]))),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT fk_registered_address_id FOREIGN KEY (registered_address_id) REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id) REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--rollback not required
