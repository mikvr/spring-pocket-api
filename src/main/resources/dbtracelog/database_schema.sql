create table "user"
(
    login     varchar(255) not null primary key,
    mail      varchar(255) not null default 0.00,
    name      varchar(255) not null default 0.00,
    img       varchar(255) not null default 0.00,
    password  varchar(255) not null,
    connected bool         not null default false
);

create table "role"
(
    id   int primary key,
    role varchar(255) not null
);

create table "users_roles"
(
    user_id varchar(255) references users(login),
    role_id int references role(id),
    primary key (user_id, role_id)
);


create table "finance"
(
    id      int          not null primary key,
    balance real         not null default 0.00,
    income  real         not null default 0.00,
    outcome real         not null default 0.00,
    user_id varchar(255) not null references users (login)
);

create table "archive"
(
    id      int  not null primary key,
    balance real not null,
    income  real not null,
    outcome real not null,
    date    timestamp
);

create table "finance_archive"
(
    id_finance int not null references finance (id),
    id_archive int not null references archive (id),
    unique (id_finance, id_archive)
);

create table "monthly_transaction"
(
    date       date not null,
    balance    real not null default 0.00,
    income     real not null default 0.00,
    outcome    real not null default 0.00,
    finance_id int  not null references finance (id),
    primary key (date, finance_id)
);

create table "monthly_transaction_archive"
(
    date_mt    date not null,
    finance_id int  not null,
    id_archive int  not null references archive (id),
    foreign key (date_mt, finance_id) references monthly_transaction(date, finance_id)
);

create table "label"
(
    id   int         not null primary key,
    name varchar(50) not null
);

create table "transaction"
(
    id         int       not null primary key,
    label_id   int       not null references label (id),
    amount     real      not null default 0.00,
    qty        int       not null default 0,
    unit_price real      not null default 0.00,
    date       timestamp not null,
    date_mt    date      not null,
    finance_fid int       not null,
    foreign key (date_mt, finance_fid) references monthly_transaction (date, finance_id)
);
