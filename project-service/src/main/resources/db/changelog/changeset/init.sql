create sequence hibernate_sequence start 1 increment 1;
create table tmp_project
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    project      varchar(255),
    description  varchar(255),
    owner_id     int8,
    title        varchar(255),
    version      varchar(255),
    workspace_id int8,
    primary key (id)
);
create table tmp_project_tasks
(
    project_id int8 not null,
    tasks_id   int8 not null
);
create table tmp_task
(
    id           int8      not null,
    date_created timestamp not null,
    date_updated timestamp,
    executor_id  int8,
    interval     int8,
    owner_id     int8,
    status       varchar(255),
    subject      varchar(255),
    text         varchar(255),
    type         varchar(255),
    project_id   int8,
    primary key (id)
);
alter table if exists tmp_project_tasks
    add constraint UK_1tucoyh7bmg0qrbe59xup9lm7 unique (tasks_id);
alter table if exists tmp_project_tasks
    add constraint FKfa41io9tirgcbej8u5uuh57q7 foreign key (tasks_id) references tmp_task;
alter table if exists tmp_project_tasks
    add constraint FKgd5uv9iqq2hp2ejm93ukngb9h foreign key (project_id) references tmp_project;
alter table if exists tmp_task
    add constraint FKikfglpgqrvh3g6nxwg17aglf1 foreign key (project_id) references tmp_project;
