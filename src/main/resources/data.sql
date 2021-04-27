/*INSERT INTO base_entity (id) Values(1),(2);*/

INSERT INTO role (name,id) Values('Customer',1);
INSERT INTO role (name,id) Values('Seller',2);

INSERT INTO User Values(1,'Harry','Potter',1);
INSERT INTO User Values(2,'Ron','Weasley',1);
INSERT INTO User Values(3,'Hermione','Granger',1);

INSERT INTO User Values(4,'Albus','Dumbledore',2);
INSERT INTO User Values(5,'Minerva','McGonagall',2);

INSERT INTO User Values(6,'Nevile','Longbottom',1);
INSERT INTO User Values(7,'Luna','Lovegood',1);
INSERT INTO User Values(8,'Ginny','Weasley',1);

INSERT INTO Book Values(1,'Harry Potter and the Philosophers Stone', 223 , 10.99 , 1);
INSERT INTO Book Values(2,'Harry Potter and the Chamber of Secrets', 251 , 10.99 , 1);
INSERT INTO Book Values(3,'Harry Potter and the Prisoner of Azkaban', 317 , 10.99 , 2);
INSERT INTO Book Values(4,'Harry Potter and the Goblet of Fire ', 636 , 15.99 , 2);
INSERT INTO Book Values(5,'Harry Potter and the Order of the Phoenix ', 766 , 15.99 , 3);
INSERT INTO Book Values(6,'Harry Potter and the Half-Blood Prince',607 , 15.99 , 3);
INSERT INTO Book Values(7,'Harry Potter and the Deathly Hallows', 607 , 15.99, 4);
INSERT INTO Book (id,name,nbpages,price) Values(8,'Harry Potter and the Philosophers Stone', 223 , 10.99);
INSERT INTO Book (id,name,nbpages,price) Values(9,'Harry Potter and the Chamber of Secrets', 251 , 10.99);
INSERT INTO Book (id,name,nbpages,price) Values(10,'Harry Potter and the Prisoner of Azkaban', 317 , 10.99);
INSERT INTO Book (id,name,nbpages,price) Values(11,'Harry Potter and the Goblet of Fire ', 636 , 15.99 );
INSERT INTO Book (id,name,nbpages,price) Values(12,'Harry Potter and the Order of the Phoenix ', 766 , 15.99);
INSERT INTO Book (id,name,nbpages,price) Values(13,'Harry Potter and the Half-Blood Prince',607 , 15.99 );
INSERT INTO Book (id,name,nbpages,price) Values(14,'Harry Potter and the Deathly Hallows', 607 , 15.99);