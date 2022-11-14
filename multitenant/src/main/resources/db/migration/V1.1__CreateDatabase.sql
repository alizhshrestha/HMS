CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v4();

CREATE TABLE accounts
(
    id bigint NOT NULL,
    uuid uuid DEFAULT uuid_generate_v4(),
    title character varying(100) NOT NULL COLLATE pg_catalog."default",
    address_line1 character varying(100) NOT NULL COLLATE pg_catalog."default",
    address_line2 character varying(50) COLLATE pg_catalog."default",
    city character varying(100) NOT NULL COLLATE pg_catalog."default",
    country character varying(100) NOT NULL COLLATE pg_catalog."default",
    zip character varying(50) NOT NULL COLLATE pg_catalog."default",
    logo character varying(256) NOT NULL COLLATE pg_catalog."default",
    favicon character varying(256) COLLATE pg_catalog."default",
    email character varying(50) NOT NULL COLLATE pg_catalog."default",
    contact character varying(50) COLLATE pg_catalog."default",
    phone character varying(50) COLLATE pg_catalog."default",
    meta_title character varying(100) NOT NULL COLLATE pg_catalog."default",
    meta_keyword character varying(100) COLLATE pg_catalog."default",
    meta_description text COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE accounts OWNER to postgres;

CREATE TABLE admins
(
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    middle_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    last_name character varying(100) COLLATE pg_catalog."default",
    email character varying(50) NOT NULL COLLATE pg_catalog."default",
    password character varying(100) NOT NULL COLLATE pg_catalog."default",
    status boolean DEFAULT FALSE,
    is_verified boolean DEFAULT FALSE,
    remember_token character varying(256) NOT NULL COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT admins_pkey PRIMARY KEY (id)
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE admins OWNER to postgres;

CREATE TABLE users
(
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    middle_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    last_name character varying(100) COLLATE pg_catalog."default",
    email character varying(50) NOT NULL COLLATE pg_catalog."default",
    phone character varying(20) NOT NULL COLLATE pg_catalog."default",
    address character varying(100) NOT NULL COLLATE pg_catalog."default",
    image character varying(100) NOT NULL COLLATE pg_catalog."default",
    password character varying(100) NOT NULL COLLATE pg_catalog."default",
    status boolean DEFAULT FALSE,
    is_verified boolean DEFAULT FALSE,
    remember_token character varying(256) COLLATE pg_catalog."default",
    last_active date,
    deleted_at date,
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT users_pkey PRIMARY KEY (id)

)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE users OWNER to postgres;


CREATE TABLE account_admins
(
    id bigint NOT NULL,
    uuid uuid DEFAULT uuid_generate_v4(),
    admin_id bigint NOT NULL,
    account_id bigint NOT NULL,
    address_line2 character varying(50) COLLATE pg_catalog."default",
    is_invitation character varying(100) NOT NULL COLLATE pg_catalog."default",
    invited_by_id character varying(100) NOT NULL COLLATE pg_catalog."default",
    is_active boolean NOT NULL,
    activated_date date NOT NULL,
    activated_reason character varying(256) COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT account_admins_pkey PRIMARY KEY (id),
    CONSTRAINT admin_id_accounts_admins_fkey FOREIGN KEY (admin_id)
        REFERENCES admins (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT account_id_accounts_admins_fkey FOREIGN KEY (account_id)
    REFERENCES accounts (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE account_admins OWNER to postgres;

CREATE TABLE otp_services
(
    id uuid DEFAULT uuid_generate_v4(),
    otp integer NOT NULL,
    otp_generated_date date NOT NULL,
    otp_valid_minutes smallint NOT NULL,
    otp_for character varying(80) NOT NULL COLLATE pg_catalog."default",
    otp_service_type character varying(50) NOT NULL COLLATE pg_catalog."default",
    verified_date date,
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT otp_services_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE otp_services OWNER to postgres;

CREATE TABLE roles
(
    id uuid DEFAULT uuid_generate_v4(),
    name character varying(100) NOT NULL COLLATE pg_catalog."default",
    guard_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE roles OWNER to postgres;

CREATE TABLE permissions
(
    id uuid DEFAULT uuid_generate_v4(),
    name character varying(100) NOT NULL COLLATE pg_catalog."default",
    guard_name character varying(100) NOT NULL COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT permission_pkey PRIMARY KEY (id)
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE permissions OWNER to postgres;

CREATE TABLE role_has_permissions
(
    id uuid DEFAULT uuid_generate_v4(),
    role_id uuid NOT NULL,
    permission_id uuid NOT NULL,
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT role_has_permission_pkey PRIMARY KEY (id),
    CONSTRAINT role_id_role_has_permission_fkey FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT permission_id_role_has_permission_fkey FOREIGN KEY (permission_id)
        REFERENCES permissions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE role_has_permissions OWNER to postgres;

CREATE TABLE admin_has_roles
(
    id uuid DEFAULT uuid_generate_v4(),
    role_id uuid NOT NULL,
    admin_id bigint NOT NULL,
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT admin_has_roles_pkey PRIMARY KEY (id),
    CONSTRAINT role_id_admin_has_roles_fkey FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT admin_id_admin_has_roles_fkey FOREIGN KEY (admin_id)
        REFERENCES admins (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE admin_has_roles OWNER to postgres;

CREATE TABLE user_social_login
(
    id uuid DEFAULT uuid_generate_v4(),
    user_id bigint NOT NULL,
    username character varying(256) NOT NULL COLLATE pg_catalog."default",
    provider character varying(30) NOT NULL COLLATE pg_catalog."default",
    provider_id character varying(100) NOT NULL COLLATE pg_catalog."default",
    image character varying(100) NOT NULL COLLATE pg_catalog."default",
    created_at date NOT NULL,
    updated_at date,
    CONSTRAINT user_social_login_pkey PRIMARY KEY (id),
    CONSTRAINT user_id_user_social_login_fkey FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE user_social_login OWNER to postgres;





