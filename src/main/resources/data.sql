# create table if not exists roles
# (
#     id        bigint auto_increment
#         primary key,
#     authority varchar(255) null
# )
#     engine = MyISAM;
#
# REPLACE INTO `roles` VALUES (1,'ADMIN'), (2,'USER');
#
# create table if not exists users
# (
#     id         bigint auto_increment
#         primary key,
#     age        int          not null,
#     email      varchar(255) null,
#     first_name varchar(255) null,
#     last_name  varchar(255) null,
#     password   varchar(255) null
# )
#     engine = MyISAM;
#
# REPLACE INTO `users` VALUES (1, 0, 'admin@kata.academy', 'JM', 'Kata', 'admin');
#
# create table if not exists user_role
# (
#     user_id        bigint not null,
#     authorities_id bigint not null
# )
#     engine = MyISAM;
#
# create index FKbij5vhnqbwdxnmoe1w7h9xpjs
#     on user_role (authorities_id);
#
# create index FKj345gk1bovqvfame88rcx7yyx
#     on user_role (user_id);