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



-- Third batch of 5 shows
-- Insert directors (new if not already existing)
INSERT INTO directors (id, name) VALUES
(11, 'Neil Cross'),
(12, 'Peter Morgan'),
(13, 'Steven Moffat'),
(14, 'Charlie Brooker');

-- Insert shows (ids continue from 10)
INSERT INTO shows (id, name, network_id, director_id) VALUES
(11, 'Luther', 5, 11),           -- BBC, Neil Cross
(12, 'The Crown', 3, 12),        -- Netflix, Peter Morgan
(13, 'House of Cards', 3, 6),    -- Netflix, David Fincher (existing id=6)
(14, 'Sherlock', 5, 13),         -- BBC, Steven Moffat
(15, 'Black Mirror', 3, 14);     -- Netflix, Charlie Brooker

-- Insert seasons (each with 3 seasons)
INSERT INTO seasons (id, name, summary, show_id) VALUES
(31, 'Luther S1', 'Season 1 of Luther', 11),
(32, 'Luther S2', 'Season 2 of Luther', 11),
(33, 'Luther S3', 'Season 3 of Luther', 11),

(34, 'The Crown S1', 'Season 1 of The Crown', 12),
(35, 'The Crown S2', 'Season 2 of The Crown', 12),
(36, 'The Crown S3', 'Season 3 of The Crown', 12),

(37, 'House of Cards S1', 'Season 1 of House of Cards', 13),
(38, 'House of Cards S2', 'Season 2 of House of Cards', 13),
(39, 'House of Cards S3', 'Season 3 of House of Cards', 13),

(40, 'Sherlock S1', 'Season 1 of Sherlock', 14),
(41, 'Sherlock S2', 'Season 2 of Sherlock', 14),
(42, 'Sherlock S3', 'Season 3 of Sherlock', 14),

(43, 'Black Mirror S1', 'Season 1 of Black Mirror', 15),
(44, 'Black Mirror S2', 'Season 2 of Black Mirror', 15),
(45, 'Black Mirror S3', 'Season 3 of Black Mirror', 15);

-- Insert writers (new or reuse)
INSERT INTO writers (id, name) VALUES
(16, 'Neil Cross'),
(17, 'Peter Morgan'),
(18, 'Beau Willimon'),
(19, 'Steven Moffat'),
(20, 'Mark Gatiss'),
(21, 'Charlie Brooker');

-- Insert actors (1-2 per show)
INSERT INTO actors (id, name) VALUES
(21, 'Idris Elba'),
(22, 'Ruth Wilson'),
(23, 'Claire Foy'),
(24, 'Matt Smith'),
(25, 'Kevin Spacey'),
(26, 'Robin Wright'),
(27, 'Benedict Cumberbatch'),
(28, 'Martin Freeman'),
(29, 'Bryce Dallas Howard'),
(30, 'Daniel Kaluuya');

-- Link shows to writers
INSERT INTO shows_writers (id, show_id, writer_id) VALUES
(16, 11, 16), -- Luther: Neil Cross

(17, 12, 17), -- The Crown: Peter Morgan

(18, 13, 18), -- House of Cards: Beau Willimon

(19, 14, 19), -- Sherlock: Steven Moffat
(20, 14, 20), -- Sherlock: Mark Gatiss

(21, 15, 21); -- Black Mirror: Charlie Brooker

-- Link shows to actors
INSERT INTO shows_actors (id, show_id, actor_id) VALUES
(21, 11, 21), -- Luther: Idris Elba
(22, 11, 22), -- Luther: Ruth Wilson

(23, 12, 23), -- The Crown: Claire Foy
(24, 12, 24), -- The Crown: Matt Smith

(25, 13, 25), -- House of Cards: Kevin Spacey
(26, 13, 26), -- House of Cards: Robin Wright

(27, 14, 27), -- Sherlock: Benedict Cumberbatch
(28, 14, 28), -- Sherlock: Martin Freeman

(29, 15, 29), -- Black Mirror: Bryce Dallas Howard
(30, 15, 30); -- Black Mirror: Daniel Kaluuya

-- Link shows to genres (up to 2 per show)
INSERT INTO shows_genres (id, show_id, genre_id) VALUES
(21, 11, 1), -- Luther: Drama
(22, 11, 2), -- Luther: Crime

(23, 12, 1), -- The Crown: Drama
(24, 12, 7), -- The Crown: Thriller

(25, 13, 1), -- House of Cards: Drama
(26, 13, 2), -- House of Cards: Crime

(27, 14, 1), -- Sherlock: Drama
(28, 14, 2), -- Sherlock: Crime

(29, 15, 5), -- Black Mirror: Sci-Fi
(30, 15, 7); -- Black Mirror: Thriller



-- Fourth batch of 5 shows
-- Insert directors (new if not already existing)
INSERT INTO directors (id, name) VALUES
(15, 'Tim Miller'),
(16, 'Noah Hawley'),
(17, 'José Padilha'),
(18, 'Eric Kripke');

-- Insert shows (ids continue from 15)
INSERT INTO shows (id, name, network_id, director_id) VALUES
(16, 'Love, Death & Robots', 3, 15), -- Netflix, Tim Miller
(17, 'Fargo', 2, 16),                -- HBO, Noah Hawley
(18, 'Narcos', 3, 17),               -- Netflix, José Padilha
(19, 'The Boys', 4, 18),             -- Amazon Prime, Eric Kripke
(20, 'Breaking Bad', 1, 1);          -- AMC, Vince Gilligan (existing id=1)

-- Insert seasons (each with 3 seasons)
INSERT INTO seasons (id, name, summary, show_id) VALUES
(46, 'Love, Death & Robots S1', 'Season 1 of Love, Death & Robots', 16),
(47, 'Love, Death & Robots S2', 'Season 2 of Love, Death & Robots', 16),
(48, 'Love, Death & Robots S3', 'Season 3 of Love, Death & Robots', 16),

(49, 'Fargo S1', 'Season 1 of Fargo', 17),
(50, 'Fargo S2', 'Season 2 of Fargo', 17),
(51, 'Fargo S3', 'Season 3 of Fargo', 17),

(52, 'Narcos S1', 'Season 1 of Narcos', 18),
(53, 'Narcos S2', 'Season 2 of Narcos', 18),
(54, 'Narcos S3', 'Season 3 of Narcos', 18),

(55, 'The Boys S1', 'Season 1 of The Boys', 19),
(56, 'The Boys S2', 'Season 2 of The Boys', 19),
(57, 'The Boys S3', 'Season 3 of The Boys', 19),

(58, 'Breaking Bad S1', 'Season 1 of Breaking Bad', 20),
(59, 'Breaking Bad S2', 'Season 2 of Breaking Bad', 20),
(60, 'Breaking Bad S3', 'Season 3 of Breaking Bad', 20);

-- Insert writers (new or reuse)
INSERT INTO writers (id, name) VALUES
(22, 'Tim Miller'),
(23, 'Noah Hawley'),
(24, 'Chris Brancato'),
(25, 'Eric Kripke'),
(26, 'Vince Gilligan');

-- Insert actors (1-2 per show)
INSERT INTO actors (id, name) VALUES
(31, 'Scott Whyte'),
(32, 'Nolan North'),
(33, 'Billy Bob Thornton'),
(34, 'Martin Freeman'),
(35, 'Wagner Moura'),
(36, 'Pedro Pascal'),
(37, 'Karl Urban'),
(38, 'Jack Quaid'),
(39, 'Bryan Cranston'),
(40, 'Aaron Paul');

-- Link shows to writers
INSERT INTO shows_writers (id, show_id, writer_id) VALUES
(22, 16, 22), -- Love, Death & Robots: Tim Miller

(23, 17, 23), -- Fargo: Noah Hawley

(24, 18, 24), -- Narcos: Chris Brancato

(25, 19, 25), -- The Boys: Eric Kripke

(26, 20, 26); -- Breaking Bad: Vince Gilligan

-- Link shows to actors
INSERT INTO shows_actors (id, show_id, actor_id) VALUES
(31, 16, 31), -- Love, Death & Robots: Scott Whyte
(32, 16, 32), -- Love, Death & Robots: Nolan North

(33, 17, 33), -- Fargo: Billy Bob Thornton
(34, 17, 34), -- Fargo: Martin Freeman

(35, 18, 35), -- Narcos: Wagner Moura
(36, 18, 36), -- Narcos: Pedro Pascal

(37, 19, 37), -- The Boys: Karl Urban
(38, 19, 38), -- The Boys: Jack Quaid

(39, 20, 39), -- Breaking Bad: Bryan Cranston
(40, 20, 40); -- Breaking Bad: Aaron Paul

-- Link shows to genres (up to 2 per show)
INSERT INTO shows_genres (id, show_id, genre_id) VALUES
(31, 16, 5), -- Love, Death & Robots: Sci-Fi
(32, 16, 7), -- Love, Death & Robots: Thriller

(33, 17, 1), -- Fargo: Drama
(34, 17, 2), -- Fargo: Crime

(35, 18, 1), -- Narcos: Drama
(36, 18, 2), -- Narcos: Crime

(37, 19, 4), -- The Boys: Superhero
(38, 19, 1), -- The Boys: Drama

(39, 20, 1), -- Breaking Bad: Drama
(40, 20, 2); -- Breaking Bad: Crime