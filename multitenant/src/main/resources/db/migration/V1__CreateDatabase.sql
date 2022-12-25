CREATE SEQUENCE IF NOT EXISTS seq_account START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_admin START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_user START WITH 1 INCREMENT BY 1;

CREATE TABLE accounts
(
    id               INTEGER NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    uuid             UUID,
    title            VARCHAR(100),
    description      TEXT,
    address_line1    VARCHAR(100),
    address_line2    VARCHAR(50),
    city             VARCHAR(100),
    country          VARCHAR(100),
    zip              VARCHAR(50),
    logo             VARCHAR(256),
    favicon          VARCHAR(256),
    email            VARCHAR(50),
    contact          VARCHAR(50),
    phone            VARCHAR(50),
    meta_title       VARCHAR(100),
    meta_keyword     VARCHAR(100),
    meta_description TEXT,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE admin_has_roles
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    role_id    UUID,
    admin_id   INTEGER,
    CONSTRAINT pk_admin_has_roles PRIMARY KEY (id)
);

CREATE TABLE admins
(
    id             INTEGER NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    first_name     VARCHAR(100),
    middle_name    VARCHAR(100),
    last_name      VARCHAR(100),
    email          VARCHAR(50),
    password       VARCHAR(100),
    status         BOOLEAN NOT NULL,
    is_verified    BOOLEAN NOT NULL,
    remember_token VARCHAR(256),
    CONSTRAINT pk_admins PRIMARY KEY (id)
);

CREATE TABLE otp_services
(
    id                 UUID    NOT NULL,
    created_at         TIMESTAMP WITHOUT TIME ZONE,
    updated_at         TIMESTAMP WITHOUT TIME ZONE,
    otp                INTEGER,
    otp_generated_date TIMESTAMP WITHOUT TIME ZONE,
    otp_valid_minutes  INTEGER NOT NULL,
    otp_for            VARCHAR(80),
    otp_service_type   VARCHAR(50),
    verified_date      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_otp_services PRIMARY KEY (id)
);

CREATE TABLE permissions
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100),
    guard_name VARCHAR(100),
    CONSTRAINT pk_permissions PRIMARY KEY (id)
);

CREATE TABLE role_has_permissions
(
    id            UUID NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    role_id       UUID,
    permission_id UUID,
    CONSTRAINT pk_role_has_permissions PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100),
    guard_name VARCHAR(100),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE user_social_login
(
    id          UUID NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    user_id     INTEGER,
    username    VARCHAR(256),
    provider    VARCHAR(30),
    provider_id VARCHAR(100),
    image       VARCHAR(255),
    CONSTRAINT pk_user_social_login PRIMARY KEY (id)
);

CREATE TABLE users
(
    id             INTEGER NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    first_name     VARCHAR(100),
    middle_name    VARCHAR(100),
    last_name      VARCHAR(100),
    email          VARCHAR(50),
    phone          VARCHAR(20),
    address        VARCHAR(100),
    image          VARCHAR(100),
    password       VARCHAR(100),
    status         BOOLEAN,
    is_verified    BOOLEAN,
    remember_token VARCHAR(256),
    last_active    TIMESTAMP WITHOUT TIME ZONE,
    deleted_at     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE admin_has_roles
    ADD CONSTRAINT FK_ADMIN_HAS_ROLES_ON_ADMIN FOREIGN KEY (admin_id) REFERENCES admins (id);

ALTER TABLE admin_has_roles
    ADD CONSTRAINT FK_ADMIN_HAS_ROLES_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE role_has_permissions
    ADD CONSTRAINT FK_ROLE_HAS_PERMISSIONS_ON_PERMISSION FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE role_has_permissions
    ADD CONSTRAINT FK_ROLE_HAS_PERMISSIONS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_social_login
    ADD CONSTRAINT FK_USER_SOCIAL_LOGIN_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);