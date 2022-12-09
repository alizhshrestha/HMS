CREATE SCHEMA tenant2
    AUTHORIZATION postgres;

CREATE TABLE tenant2.account_admins
(
    id               INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    uuid             UUID,
    admin_id         INTEGER,
    account_id       INTEGER,
    is_invitation    VARCHAR(100),
    invited_by_id    VARCHAR(100),
    is_active        BOOLEAN                                  NOT NULL,
    activated_date   TIMESTAMP WITHOUT TIME ZONE,
    activated_reason VARCHAR(256),
    CONSTRAINT pk_account_admins_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.accounts
(
    id               INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
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
    CONSTRAINT pk_accounts_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.admin_has_roles
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    role_id    UUID,
    admin_id   INTEGER,
    CONSTRAINT pk_admin_has_roles_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.admins
(
    id             INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    first_name     VARCHAR(100),
    middle_name    VARCHAR(100),
    last_name      VARCHAR(100),
    email          VARCHAR(50),
    password       VARCHAR(100),
    status         BOOLEAN                                  NOT NULL,
    is_verified    BOOLEAN                                  NOT NULL,
    remember_token VARCHAR(256),
    CONSTRAINT pk_admins_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.otp_services
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
    CONSTRAINT pk_otp_services_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.permissions
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100),
    guard_name VARCHAR(100),
    CONSTRAINT pk_permissions_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.role_has_permissions
(
    id            UUID NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    role_id       UUID,
    permission_id UUID,
    CONSTRAINT pk_role_has_permissions_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.roles
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100),
    guard_name VARCHAR(100),
    CONSTRAINT pk_roles_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.user_social_login
(
    id          UUID NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    user_id     INTEGER,
    username    VARCHAR(256),
    provider    VARCHAR(30),
    provider_id VARCHAR(100),
    image       VARCHAR(255),
    CONSTRAINT pk_user_social_login_tenant2 PRIMARY KEY (id)
);

CREATE TABLE tenant2.users
(
    id             INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
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
    CONSTRAINT pk_users_tenant2 PRIMARY KEY (id)
);

ALTER TABLE account_admins
    ADD CONSTRAINT FK_ACCOUNT_ADMINS_ON_ACCOUNT_tenant2 FOREIGN KEY (account_id) REFERENCES tenant2.accounts (id);

ALTER TABLE account_admins
    ADD CONSTRAINT FK_ACCOUNT_ADMINS_ON_ADMIN_tenant2 FOREIGN KEY (admin_id) REFERENCES tenant2.admins (id);

ALTER TABLE admin_has_roles
    ADD CONSTRAINT FK_ADMIN_HAS_ROLES_ON_ADMIN_tenant2 FOREIGN KEY (admin_id) REFERENCES tenant2.admins (id);

ALTER TABLE admin_has_roles
    ADD CONSTRAINT FK_ADMIN_HAS_ROLES_ON_ROLE_tenant2 FOREIGN KEY (role_id) REFERENCES tenant2.roles (id);

ALTER TABLE role_has_permissions
    ADD CONSTRAINT FK_ROLE_HAS_PERMISSIONS_ON_PERMISSION_tenant2 FOREIGN KEY (permission_id) REFERENCES tenant2.permissions (id);

ALTER TABLE role_has_permissions
    ADD CONSTRAINT FK_ROLE_HAS_PERMISSIONS_ON_ROLE_tenant2 FOREIGN KEY (role_id) REFERENCES tenant2.roles (id);

ALTER TABLE user_social_login
    ADD CONSTRAINT FK_USER_SOCIAL_LOGIN_ON_USER_tenant2 FOREIGN KEY (user_id) REFERENCES tenant2.users (id);