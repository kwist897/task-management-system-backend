create sequence hibernate_sequence start 1 increment 1;
create table tmp_group
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    description  varchar(255),
    is_private   boolean,
    title        varchar(255),
    created_by   int8,
    updated_by   int8,
    primary key (id)
);
create table tmp_group_profile
(
    group_id   int8 not null,
    profile_id int8 not null,
    primary key (group_id, profile_id)
);
create table tmp_profile
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    first_name   varchar(255),
    last_name    varchar(255),
    user_id      int8,
    primary key (id)
);
create table tmp_workspace
(
    id             int8      not null,
    date_created   timestamp not null,
    date_updated   timestamp,
    description    varchar(255),
    title          varchar(255),
    workspace_type varchar(255),
    created_by     int8,
    group_id       int8,
    primary key (id)
);
alter table if exists tmp_profile
    add constraint UK_cydhdmsvt9t5p8hmcobst84gf unique (user_id);
alter table if exists tmp_group
    add constraint FKjdp7oqxhio6jnj9f3ptnyxxqu foreign key (created_by) references tmp_profile;
alter table if exists tmp_group
    add constraint FKny0sbi06bvydppeqontpv4g91 foreign key (updated_by) references tmp_profile;
alter table if exists tmp_group_profile
    add constraint FKqg5lg5qtmn22rmcvh2dvivsu7 foreign key (profile_id) references tmp_profile;
alter table if exists tmp_group_profile
    add constraint FK9qgxd80siyawl0q4c3jag5qi3 foreign key (group_id) references tmp_group;
alter table if exists tmp_workspace
    add constraint FKt9i75uwop0ujdmkn0i1qk152f foreign key (created_by) references tmp_profile;
alter table if exists tmp_workspace
    add constraint FK6h255elrr64x61kwfq5j139eu foreign key (group_id) references tmp_group;
