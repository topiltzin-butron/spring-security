CREATE TABLE IF NOT EXISTS taleme_ks.greetings (
    user text,
    id timeuuid,
    greet text,
    creation_date timestamp,
    PRIMARY KEY (user, id)
) WITH CLUSTERING ORDER BY (id DESC);

CREATE TABLE user_roles (
    role text,
    username text,
    password text,
    email text,
    age int,
    PRIMARY KEY (username, role)
);
