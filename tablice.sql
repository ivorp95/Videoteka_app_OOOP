

CREATE table clanVideoteka(
clan_id int primary key auto_increment not null,
ime varchar(30) not null,
prezime varchar(50) not null,
broj_mob varchar(20) not null,
lozinka varchar(70) not null
);



alter table clanVideoteka add lozinka varchar(70) not null;



CREATE table filmVideoteka(
film_id int primary key auto_increment not null,
naslov varchar(100) not null,
redatelj varchar(100) not null,
trajanje varchar(15) not null,
god_izdanja varchar(15) not null
);



create table posudbaVideoteka(
id_posudba int auto_increment primary key not null,
clan_id int not null,
film_id int not null,
datum_posudbe date not null,
datum_vracanja date not null,
foreign key (film_id) references filmVideoteka(film_id) on delete restrict on update cascade,
foreign key (clan_id) references clanVideoteka(clan_id) on delete cascade on update cascade
);

