create sequence hibernate_sequence start 1 increment 1;
create table tmp_access_token
(
    id              varchar(255) not null,
    date_created    timestamp    not null,
    date_updated    timestamp,
    expiration_date timestamp,
    token           text,
    user_id         int8,
    primary key (id)
);
create table tmp_refresh_token
(
    id              varchar(255) not null,
    date_created    timestamp    not null,
    date_updated    timestamp,
    expiration_date timestamp,
    token           text,
    user_id         int8,
    primary key (id)
);
create table tmp_role
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    role_name    varchar(255),
    primary key (id)
);
create table tmp_user
(
    id            int8      not null,
    date_created  timestamp not null,
    date_updated  timestamp,
    account_type  varchar(255),
    auth_provider varchar(255),
    email         varchar(255),
    enabled       boolean,
    password      varchar(255),
    username      varchar(255),
    primary key (id)
);
create table tmp_user_role
(
    user_id int8 not null,
    role_id int8 not null
);
create table tmp_user_workspace
(
    user_id      int8 not null,
    workspace_id int8 not null
);
create table tmp_workspace
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    description  varchar(255),
    title        varchar(255),
    primary key (id)
);
alter table if exists tmp_access_token
    add constraint FKt6u7ll6c4r6dthjqlv5n46hgj foreign key (user_id) references tmp_user;
alter table if exists tmp_refresh_token
    add constraint FKlhcmbqa458q9q2ro5l05um53r foreign key (user_id) references tmp_user;
alter table if exists tmp_user_role
    add constraint FK2dksb713qe1vrm4alld1hhnpl foreign key (role_id) references tmp_role;
alter table if exists tmp_user_role
    add constraint FKtqiybrsp00yeyrde94abk3ytl foreign key (user_id) references tmp_user;
alter table if exists tmp_user_workspace
    add constraint FK68mfcku52iunk6cltds2fhyhm foreign key (workspace_id) references tmp_workspace;
alter table if exists tmp_user_workspace
    add constraint FKjdgce2mgd6aeoaiypkpw8qgdw foreign key (user_id) references tmp_user;


create unique index username_index on tmp_user (username);