/*
	This script will populate all tables except for those relating
    to user, progress list data, or episodes. For episodes, see
    insert_episodes.sql. User and list data is added through
    BingeBoard app. This script will written in batches of 5 shows.
*/

-- First batch of 5 shows
-- Insert networks (assumed unique names)
INSERT INTO networks (id, name) VALUES
(1, 'AMC'),
(2, 'HBO'),
(3, 'Netflix'),
(4, 'Amazon Prime'),
(5, 'BBC');

-- Insert directors (one per show here)
INSERT INTO directors (id, name) VALUES
(1, 'Vince Gilligan'),
(2, 'Adam McKay'),
(3, 'Steve Blackman'),
(4, 'Baran bo Odar'),
(5, 'Lauren Schmidt Hissrich');

-- Insert shows with network_id and director_id mapped
INSERT INTO shows (id, name, network_id, director_id) VALUES
(1, 'Better Call Saul', 1, 1),
(2, 'Succession', 2, 2),
(3, 'The Umbrella Academy', 3, 3),
(4, 'Dark', 3, 4),
(5, 'The Witcher', 3, 5);

-- Insert seasons for these shows (each has 3 seasons)
INSERT INTO seasons (id, name, summary, show_id) VALUES
(1, 'Better Call Saul S1', 'Season 1 of Better Call Saul', 1),
(2, 'Better Call Saul S2', 'Season 2 of Better Call Saul', 1),
(3, 'Better Call Saul S3', 'Season 3 of Better Call Saul', 1),

(4, 'Succession S1', 'Season 1 of Succession', 2),
(5, 'Succession S2', 'Season 2 of Succession', 2),
(6, 'Succession S3', 'Season 3 of Succession', 2),

(7, 'The Umbrella Academy S1', 'Season 1 of The Umbrella Academy', 3),
(8, 'The Umbrella Academy S2', 'Season 2 of The Umbrella Academy', 3),
(9, 'The Umbrella Academy S3', 'Season 3 of The Umbrella Academy', 3),

(10, 'Dark S1', 'Season 1 of Dark', 4),
(11, 'Dark S2', 'Season 2 of Dark', 4),
(12, 'Dark S3', 'Season 3 of Dark', 4),

(13, 'The Witcher S1', 'Season 1 of The Witcher', 5),
(14, 'The Witcher S2', 'Season 2 of The Witcher', 5),
(15, 'The Witcher S3', 'Season 3 of The Witcher', 5);

-- Insert writers (1-2 per show)
INSERT INTO writers (id, name) VALUES
(1, 'Peter Gould'),
(2, 'Thomas Schnauz'),
(3, 'Jesse Armstrong'),
(4, 'Lucy Prebble'),
(5, 'Steve Blackman'),
(6, 'Gerard Way'),
(7, 'Baran bo Odar'),
(8, 'Jantje Friese'),
(9, 'Lauren Schmidt Hissrich'),
(10, 'Beau DeMayo');

-- Insert actors (1-2 per show)
INSERT INTO actors (id, name) VALUES
(1, 'Bob Odenkirk'),
(2, 'Rhea Seehorn'),
(3, 'Brian Cox'),
(4, 'Jeremy Strong'),
(5, 'Elliot Page'),
(6, 'Tom Hopper'),
(7, 'Louis Hofmann'),
(8, 'Lisa Vicari'),
(9, 'Henry Cavill'),
(10, 'Anya Chalotra');

-- Link shows to writers
INSERT INTO shows_writers (id, show_id, writer_id) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 2, 4),
(5, 3, 5),
(6, 3, 6),
(7, 4, 7),
(8, 4, 8),
(9, 5, 9),
(10, 5, 10);

-- Link shows to actors
INSERT INTO shows_actors (id, show_id, actor_id) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 2, 4),
(5, 3, 5),
(6, 3, 6),
(7, 4, 7),
(8, 4, 8),
(9, 5, 9),
(10, 5, 10);

-- Insert genres
INSERT INTO genres (id, name) VALUES
(1, 'Drama'),
(2, 'Crime'),
(3, 'Comedy'),
(4, 'Superhero'),
(5, 'Sci-Fi'),
(6, 'Fantasy'),
(7, 'Thriller');

-- Link shows to genres (each with up to 2 genres)
INSERT INTO shows_genres (id, show_id, genre_id) VALUES
(1, 1, 1), -- Better Call Saul: Drama
(2, 1, 2), -- Better Call Saul: Crime

(3, 2, 1), -- Succession: Drama
(4, 2, 3), -- Succession: Comedy

(5, 3, 4), -- The Umbrella Academy: Superhero
(6, 3, 1), -- The Umbrella Academy: Drama

(7, 4, 5), -- Dark: Sci-Fi
(8, 4, 7), -- Dark: Thriller

(9, 5, 6), -- The Witcher: Fantasy
(10, 5, 1); -- The Witcher: Drama



-- Second batch of 5 shows
-- Insert directors (new if not already existing)
INSERT INTO directors (id, name) VALUES
(6, 'David Fincher'),
(7, 'Cary Joji Fukunaga'),
(8, 'Steven Knight'),
(9, 'Bryan Fuller'),
(10, 'Sam Esmail');

-- Insert shows (id continues from 5)
INSERT INTO shows (id, name, network_id, director_id) VALUES
(6, 'Mindhunter', 3, 6),        -- Netflix, David Fincher
(7, 'True Detective', 2, 7),    -- HBO, Cary Joji Fukunaga
(8, 'Peaky Blinders', 5, 8),    -- BBC, Steven Knight
(9, 'Hannibal', 1, 9),          -- AMC, Bryan Fuller
(10, 'Mr. Robot', 4, 10);       -- Amazon Prime, Sam Esmail

-- Insert seasons (each with 3 seasons)
INSERT INTO seasons (id, name, summary, show_id) VALUES
(16, 'Mindhunter S1', 'Season 1 of Mindhunter', 6),
(17, 'Mindhunter S2', 'Season 2 of Mindhunter', 6),
(18, 'Mindhunter S3', 'Season 3 of Mindhunter', 6),

(19, 'True Detective S1', 'Season 1 of True Detective', 7),
(20, 'True Detective S2', 'Season 2 of True Detective', 7),
(21, 'True Detective S3', 'Season 3 of True Detective', 7),

(22, 'Peaky Blinders S1', 'Season 1 of Peaky Blinders', 8),
(23, 'Peaky Blinders S2', 'Season 2 of Peaky Blinders', 8),
(24, 'Peaky Blinders S3', 'Season 3 of Peaky Blinders', 8),

(25, 'Hannibal S1', 'Season 1 of Hannibal', 9),
(26, 'Hannibal S2', 'Season 2 of Hannibal', 9),
(27, 'Hannibal S3', 'Season 3 of Hannibal', 9),

(28, 'Mr. Robot S1', 'Season 1 of Mr. Robot', 10),
(29, 'Mr. Robot S2', 'Season 2 of Mr. Robot', 10),
(30, 'Mr. Robot S3', 'Season 3 of Mr. Robot', 10);

-- Insert writers (reuse or add new)
INSERT INTO writers (id, name) VALUES
(11, 'Joe Penhall'),
(12, 'Nic Pizzolatto'),
(13, 'Steven Knight'), -- already exists as director, but adding as writer entity
(14, 'Bryan Fuller'),  -- same note
(15, 'Sam Esmail');    -- same note

-- Insert actors (1-2 per show)
INSERT INTO actors (id, name) VALUES
(11, 'Jonathan Groff'),
(12, 'Holt McCallany'),
(13, 'Matthew McConaughey'),
(14, 'Woody Harrelson'),
(15, 'Cillian Murphy'),
(16, 'Paul Anderson'),
(17, 'Mads Mikkelsen'),
(18, 'Hugh Dancy'),
(19, 'Rami Malek'),
(20, 'Christian Slater');

-- Link shows to writers
INSERT INTO shows_writers (id, show_id, writer_id) VALUES
(11, 6, 11),  -- Mindhunter: Joe Penhall
(12, 7, 12),  -- True Detective: Nic Pizzolatto
(13, 8, 13),  -- Peaky Blinders: Steven Knight
(14, 9, 14),  -- Hannibal: Bryan Fuller
(15, 10, 15); -- Mr. Robot: Sam Esmail

-- Link shows to actors
INSERT INTO shows_actors (id, show_id, actor_id) VALUES
(11, 6, 11), -- Mindhunter: Jonathan Groff
(12, 6, 12), -- Mindhunter: Holt McCallany
(13, 7, 13), -- True Detective: Matthew McConaughey
(14, 7, 14), -- True Detective: Woody Harrelson
(15, 8, 15), -- Peaky Blinders: Cillian Murphy
(16, 8, 16), -- Peaky Blinders: Paul Anderson
(17, 9, 17), -- Hannibal: Mads Mikkelsen
(18, 9, 18), -- Hannibal: Hugh Dancy
(19, 10, 19), -- Mr. Robot: Rami Malek
(20, 10, 20); -- Mr. Robot: Christian Slater

-- Link shows to genres (up to 2 per show)
INSERT INTO shows_genres (id, show_id, genre_id) VALUES
(11, 6, 1), -- Mindhunter: Drama
(12, 6, 2), -- Mindhunter: Crime

(13, 7, 1), -- True Detective: Drama
(14, 7, 2), -- True Detective: Crime

(15, 8, 1), -- Peaky Blinders: Drama
(16, 8, 2), -- Peaky Blinders: Crime

(17, 9, 1), -- Hannibal: Drama
(18, 9, 7), -- Hannibal: Thriller

(19, 10, 1), -- Mr. Robot: Drama
(20, 10, 7); -- Mr. Robot: Thriller