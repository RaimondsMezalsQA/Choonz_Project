drop table if exists album CASCADE ;
drop table if exists artist CASCADE ;
drop table if exists genre CASCADE ;
drop table if exists playlist CASCADE ;
drop table if exists track CASCADE ;
drop table if exists user CASCADE ;
create table album (id bigint, cover varchar(255), name varchar(100) not null, artist_id bigint, genre_id bigint, primary key (id));
create table artist (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(100) not null);
create table genre (id bigint PRIMARY KEY AUTO_INCREMENT, description varchar(250) not null, name varchar(100) not null);
create table playlist (id bigint PRIMARY KEY AUTO_INCREMENT, artwork varchar(1000) not null, description varchar(500) not null, name varchar(100) not null, user_id bigint);
create table track (id bigint PRIMARY KEY AUTO_INCREMENT, duration integer not null, lyrics varchar(5000), name varchar(100) not null, album_id bigint, playlist_id bigint);
create table user (id bigint PRIMARY KEY AUTO_INCREMENT, password varchar(20) not null, username varchar(20) not null);
alter table album add constraint UK_7io8ua6qgnb7yjn96tvrakvsk unique (name);
alter table artist add constraint UK_r03a96lqhsb7djq2tn4rq60vn unique (name);
alter table genre add constraint UK_dy2vbbbf0mb52fy9tks9anm3k unique (description);
alter table genre add constraint UK_ctffrbu4484ft8dlsa5vmqdka unique (name);
alter table playlist add constraint UK_swyw77f2jscjvdd29t0s2jvfv unique (artwork);
alter table playlist add constraint UK_rqydw3x69vu1xbk7m6flxi0bk unique (description);
alter table playlist add constraint UK_gmx0jjome08oqihtks37w0lea unique (name);
alter table track add constraint UK_33mfukvakfr3t9ri1og4jhg4l unique (name);
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table album add constraint FKmwc4fyyxb6tfi0qba26gcf8s1 foreign key (artist_id) references artist;
alter table album add constraint FKmhihrmrx2f1mhqtrbagwru45g foreign key (genre_id) references genre;
alter table playlist add constraint FKlbi6wsq41356go2ki0yirfixo foreign key (user_id) references user;
alter table track add constraint FKaxm9pbgk7ptorfyk3o6911q05 foreign key (album_id) references album on delete cascade;
alter table track add constraint FKnebkq7qmpex9wivvxbfqtlkl4 foreign key (playlist_id) references playlist;