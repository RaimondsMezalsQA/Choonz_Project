INSERT INTO ARTIST (name) VALUES ('Ariana Grande');
INSERT INTO ARTIST (name) VALUES ('Justin Bieber');
INSERT INTO ARTIST (name) VALUES ('Adele');
INSERT INTO ARTIST (name) VALUES ('Bruno Mars');
INSERT INTO GENRE (name, description) VALUES ('Pop', 'Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture.');
INSERT INTO GENRE (name, description) VALUES ('Rock','Rock music is a broad genre of popular music that originated as "rock and roll" in the United States in the late 1940s and early 1950s.');
INSERT INTO GENRE (name, description) VALUES ('Country','Country (also called country and western) is a genre of popular music that originated with blues, old-time music, and various types of American folk music.');
INSERT INTO GENRE (name, description) VALUES ('Reggae','Reggae is a popular style of music that began in Jamaica and often has political and spiritual themes.');
INSERT INTO ALBUM (name,cover,artist_id,genre_id) VALUES ('Thank U, Next','https://upload.wikimedia.org/wikipedia/en/d/dd/Thank_U%2C_Next_album_cover.png',1,1);
INSERT INTO ALBUM (name,cover,artist_id,genre_id) VALUES ('24K Magic','https://upload.wikimedia.org/wikipedia/en/2/2b/Bruno_Mars_-_24K_Magic_%28Official_Album_Cover%29.png',4,1);
INSERT INTO ALBUM (name,cover,artist_id,genre_id) VALUES ('25 Album','https://upload.wikimedia.org/wikipedia/en/9/96/Adele_-_25_%28Official_Album_Cover%29.png',3,1);
INSERT INTO ALBUM (name,cover,artist_id,genre_id) VALUES ('Purpose','https://upload.wikimedia.org/wikipedia/en/2/27/Justin_Bieber_-_Purpose_%28Official_Album_Cover%29.png',2,1);
INSERT INTO USER (username,password) VALUES ('admin','admin');
INSERT INTO PLAYLIST (name,description,artwork,user_id) VALUES ('Favourites','This is a playlist by admin consisting of public favourites.','https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png',1);
INSERT INTO TRACK (name,lyrics,duration,album_id,playlist_id) VALUES ('Thank U Next','Thank U Next lyrics',207,1,1);
INSERT INTO TRACK (name,lyrics,duration,album_id,playlist_id) VALUES ('needy','needy lyrics',212,1,1);
INSERT INTO TRACK (name,lyrics,duration,album_id,playlist_id) VALUES ('24K Magic','24K Magic Lyrics',240,2,1);
INSERT INTO TRACK (name,lyrics,duration,album_id,playlist_id) VALUES ('Hello','Hello lyrics',220,3,1);
INSERT INTO TRACK (name,lyrics,duration,album_id,playlist_id) VALUES ('What Do You Mean','What Do You Mean lyrics',210,4,1);