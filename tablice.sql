

CREATE table clanVideoteka(
clan_id int primary key auto_increment not null,
ime varchar(30) not null,
prezime varchar(50) not null,
broj_mob varchar(20) not null,
lozinka varchar(70) not null




alter table clanVideoteka add lozinka varchar(70) not null;



CREATE table filmVideoteka(
film_id int primary key auto_increment not null,
naslov varchar(100) not null,
redatelj varchar(100) not null,
trajanje varchar(15) not null,
god_izdanja varchar(15) not null




create table posudbaVideoteka(
id_posudba int auto_increment primary key not null,
clan_id int not null,
film_id int not null,
datum_posudbe date not null,
datum_vracanja date not null,
foreign key (film_id) references filmVideoteka(film_id) on delete restrict on update cascade,
foreign key (clan_id) references clanVideoteka(clan_id) on delete cascade on update cascade


insert into filmVideoteka (naslov,redatelj,god_izdanja,trajanje) values
('Gladiator','Ridley Scott','2000','170'),
('Joker','Todd Philips','2019','122'),
('A Clockwork Orange','Stanley Kubrick','1971','137'),
('Dr. Strangelove','Stanley Kubrick','1963','95'),
('Mad Max: Fury Road','George Miller','2015','120'),
('Watchmen','Zack Snyder','2009','215'),
('Blade Runner (The Final Cut)','Ridley Scott','1982','119'),
('The Lord of the Rings: The Fellowship of the Ring','Peter Jackson','2001','178'),
('The Lord of the Rings: The Two Towers','Peter Jackson','2002','180'),
('The Lord of the Rings: Return of the King','Peter Jackson','2003','201'),
('Memento','Cristopher Nolan','2000','113'),
('300','Zack Snyder','2006','117'),
('Inglorious Bastards','Quentin Tarantino','2009','153')
;

########################################################################################
(1,'The Dark Knight','Cristopher Nolan','152','2008')
('2001 a Space Odyssey','Stanley Kubrick','1968','149')
('Gladiator','Ridley Scott','2000','170'),
('Joker','Todd Philips','2019','122'),
('A Clockwork Orange','Stanley Kubrick','1971','137'),
('Dr. Strangelove','Stanley Kubrick','1963','95'),
('Mad Max: Fury Road','George Miller','2015','120'),
('Watchmen','Zack Snyder','2009','215'),
('Blade Runner (The Final Cut)','Ridley Scott','1982','119'),
('The Lord of the Rings: The Fellowship of the Ring','Peter Jackson','2001','178'),
('The Lord of the Rings: The Two Towers','Peter Jackson','2002','180'),
('The Lord of the Rings: Return of the King','Peter Jackson','2003','201'),
('Memento','Cristopher Nolan','2000','113'),
('300','Zack Snyder','2006','117'),
('Inglorious Bastards','Quentin Tarantino','2009','153')








SELECT clan_id FROM clanOOOP WHERE broj_mob=(select broj_mob from clanOOOP where prezime='pangos');



select datum_vracanja, clanVideoteka.ime, clanVideoteka.broj_mob, filmVideoteka.film_id, filmVideoteka.god_izdanja  from posudbaVideoteka 
left outer join clanVideoteka on posudbaVideoteka.clan_id = clanVideoteka.clan_id left outer join filmVideoteka on filmVideoteka.film_id = posudbaVideoteka.film_id ;

SELECT * FROM filmVideoteka WHERE redatelj='Terry Gilliam' OR god_izdanja='1999';

SELECT id_posudba FROM posudbaVideoteka WHERE film_id=2;

update filmVideoteka set  naslov='naslov', redatelj='test', god_izdanja='123', trajanje='434' where film_id=44;

