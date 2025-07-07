-- ================================
-- BingeBoard Insert Script (full 20 shows aligned to insert_episodes.sql)
-- ================================

-- Insert networks
INSERT INTO networks (id, name) VALUES
(1, 'AMC'),
(2, 'HBO'),
(3, 'Netflix'),
(4, 'Amazon Prime'),
(5, 'BBC'),
(6, 'Syfy');

-- Insert directors
INSERT INTO directors (id, name) VALUES
(1, 'Vince Gilligan'),
(2, 'Adam McKay'),
(3, 'Steve Blackman'),
(4, 'Baran bo Odar'),
(5, 'Lauren Schmidt Hissrich'),
(6, 'Jonathan Nolan'),
(7, 'David Fincher'),
(8, 'Jason Bateman'),
(9, 'Steven Knight'),
(10, 'Eric Kripke'),
(11, 'Noah Hawley'),
(12, 'Jose Padilha'),
(13, 'Charlie Brooker'),
(14, 'Steven Moffat'),
(15, 'Beau Willimon'),
(16, 'Sam Esmail'),
(17, 'Neil Cross'),
(18, 'Peter Morgan'),
(19, 'Naren Shankar'),
(20, 'The Duffer Brothers');

-- Insert shows (id 1-20)
INSERT INTO shows (id, name, network_id, director_id) VALUES
(1, 'Better Call Saul', 1, 1),
(2, 'Succession', 2, 2),
(3, 'The Umbrella Academy', 3, 3),
(4, 'Dark', 3, 4),
(5, 'The Witcher', 3, 5),
(6, 'Westworld', 2, 6),
(7, 'Mindhunter', 3, 7),
(8, 'Ozark', 3, 8),
(9, 'Peaky Blinders', 5, 9),
(10, 'The Boys', 4, 10),
(11, 'Fargo', 2, 11),
(12, 'Narcos', 3, 12),
(13, 'Black Mirror', 3, 13),
(14, 'Sherlock', 5, 14),
(15, 'House of Cards', 3, 15),
(16, 'Mr. Robot', 4, 16),
(17, 'Luther', 5, 17),
(18, 'The Crown', 3, 18),
(19, 'The Expanse', 6, 19),
(20, 'Stranger Things', 3, 20);

-- Insert seasons (3 per show, id 1-60)
-- Show 1-20 mapped sequentially with seasons
INSERT INTO seasons (id, name, summary, show_id) VALUES
-- Better Call Saul
(1, 'Better Call Saul S1', 'Season 1', 1),
(2, 'Better Call Saul S2', 'Season 2', 1),
(3, 'Better Call Saul S3', 'Season 3', 1),
-- Succession
(4, 'Succession S1', 'Season 1', 2),
(5, 'Succession S2', 'Season 2', 2),
(6, 'Succession S3', 'Season 3', 2),
-- Umbrella Academy
(7, 'The Umbrella Academy S1', 'Season 1', 3),
(8, 'The Umbrella Academy S2', 'Season 2', 3),
(9, 'The Umbrella Academy S3', 'Season 3', 3),
-- Dark
(10, 'Dark S1', 'Season 1', 4),
(11, 'Dark S2', 'Season 2', 4),
(12, 'Dark S3', 'Season 3', 4),
-- Witcher
(13, 'The Witcher S1', 'Season 1', 5),
(14, 'The Witcher S2', 'Season 2', 5),
(15, 'The Witcher S3', 'Season 3', 5),
-- Westworld
(16, 'Westworld S1', 'Season 1', 6),
(17, 'Westworld S2', 'Season 2', 6),
(18, 'Westworld S3', 'Season 3', 6),
-- Mindhunter
(19, 'Mindhunter S1', 'Season 1', 7),
(20, 'Mindhunter S2', 'Season 2', 7),
(21, 'Mindhunter S3', 'Season 3', 7),
-- Ozark
(22, 'Ozark S1', 'Season 1', 8),
(23, 'Ozark S2', 'Season 2', 8),
(24, 'Ozark S3', 'Season 3', 8),
-- Peaky Blinders
(25, 'Peaky Blinders S1', 'Season 1', 9),
(26, 'Peaky Blinders S2', 'Season 2', 9),
(27, 'Peaky Blinders S3', 'Season 3', 9),
-- The Boys
(28, 'The Boys S1', 'Season 1', 10),
(29, 'The Boys S2', 'Season 2', 10),
(30, 'The Boys S3', 'Season 3', 10),
-- Fargo
(31, 'Fargo S1', 'Season 1', 11),
(32, 'Fargo S2', 'Season 2', 11),
(33, 'Fargo S3', 'Season 3', 11),
-- Narcos
(34, 'Narcos S1', 'Season 1', 12),
(35, 'Narcos S2', 'Season 2', 12),
(36, 'Narcos S3', 'Season 3', 12),
-- Black Mirror
(37, 'Black Mirror S1', 'Season 1', 13),
(38, 'Black Mirror S2', 'Season 2', 13),
(39, 'Black Mirror S3', 'Season 3', 13),
-- Sherlock
(40, 'Sherlock S1', 'Season 1', 14),
(41, 'Sherlock S2', 'Season 2', 14),
(42, 'Sherlock S3', 'Season 3', 14),
-- House of Cards
(43, 'House of Cards S1', 'Season 1', 15),
(44, 'House of Cards S2', 'Season 2', 15),
(45, 'House of Cards S3', 'Season 3', 15),
-- Mr. Robot
(46, 'Mr. Robot S1', 'Season 1', 16),
(47, 'Mr. Robot S2', 'Season 2', 16),
(48, 'Mr. Robot S3', 'Season 3', 16),
-- Luther
(49, 'Luther S1', 'Season 1', 17),
(50, 'Luther S2', 'Season 2', 17),
(51, 'Luther S3', 'Season 3', 17),
-- The Crown
(52, 'The Crown S1', 'Season 1', 18),
(53, 'The Crown S2', 'Season 2', 18),
(54, 'The Crown S3', 'Season 3', 18),
-- The Expanse
(55, 'The Expanse S1', 'Season 1', 19),
(56, 'The Expanse S2', 'Season 2', 19),
(57, 'The Expanse S3', 'Season 3', 19),
-- Stranger Things
(58, 'Stranger Things S1', 'Season 1', 20),
(59, 'Stranger Things S2', 'Season 2', 20),
(60, 'Stranger Things S3', 'Season 3', 20);

-- Insert genres (no duplicates)
INSERT INTO genres (id, name) VALUES
(1, 'Drama'),
(2, 'Crime'),
(3, 'Comedy'),
(4, 'Superhero'),
(5, 'Sci-Fi'),
(6, 'Fantasy'),
(7, 'Thriller');

-- Insert writers (sample per show, continue IDs as needed)
INSERT INTO writers (id, name) VALUES
(1, 'Peter Gould'),
(2, 'Thomas Schnauz'),
(3, 'Jesse Armstrong'),
(4, 'Lucy Prebble'),
(5, 'Gerard Way'),
(6, 'Baran bo Odar'),
(7, 'Lauren Schmidt Hissrich'),
(8, 'Jonathan Nolan'),
(9, 'David Fincher'),
(10, 'Jason Bateman'),
(11, 'Steven Knight'),
(12, 'Eric Kripke'),
(13, 'Noah Hawley'),
(14, 'Jose Padilha'),
(15, 'Charlie Brooker'),
(16, 'Steven Moffat'),
(17, 'Beau Willimon'),
(18, 'Sam Esmail'),
(19, 'Neil Cross'),
(20, 'Peter Morgan'),
(21, 'Naren Shankar'),
(22, 'The Duffer Brothers');

-- Insert actors (sample per show, continue IDs)
INSERT INTO actors (id, name) VALUES
(1, 'Bob Odenkirk'),          -- Better Call Saul
(2, 'Rhea Seehorn'),

(3, 'Brian Cox'),             -- Succession
(4, 'Jeremy Strong'),

(5, 'Elliot Page'),           -- The Umbrella Academy
(6, 'Tom Hopper'),

(7, 'Louis Hofmann'),         -- Dark
(8, 'Lisa Vicari'),

(9, 'Henry Cavill'),          -- The Witcher
(10, 'Anya Chalotra'),

(11, 'Evan Rachel Wood'),     -- Westworld
(12, 'Thandiwe Newton'),

(13, 'Jonathan Groff'),       -- Mindhunter
(14, 'Holt McCallany'),

(15, 'Jason Bateman'),        -- Ozark
(16, 'Laura Linney'),

(17, 'Cillian Murphy'),       -- Peaky Blinders
(18, 'Paul Anderson'),

(19, 'Karl Urban'),           -- The Boys
(20, 'Jack Quaid'),

(21, 'Billy Bob Thornton'),   -- Fargo
(22, 'Martin Freeman'),

(23, 'Wagner Moura'),         -- Narcos
(24, 'Pedro Pascal'),

(25, 'Bryce Dallas Howard'),  -- Black Mirror
(26, 'Daniel Kaluuya'),

(27, 'Benedict Cumberbatch'), -- Sherlock
(28, 'Martin Freeman'),

(29, 'Kevin Spacey'),         -- House of Cards
(30, 'Robin Wright'),

(31, 'Rami Malek'),           -- Mr. Robot
(32, 'Christian Slater'),

(33, 'Idris Elba'),           -- Luther
(34, 'Ruth Wilson'),

(35, 'Claire Foy'),           -- The Crown
(36, 'Matt Smith'),

(37, 'Steven Strait'),        -- The Expanse
(38, 'Dominique Tipper'),

(39, 'Millie Bobby Brown'),   -- Stranger Things
(40, 'Finn Wolfhard');

-- Link shows to writers (sample)
INSERT INTO shows_writers (id, show_id, writer_id) VALUES
(1, 1, 1),   -- Better Call Saul: Peter Gould
(2, 1, 2),   -- Better Call Saul: Thomas Schnauz

(3, 2, 3),   -- Succession: Jesse Armstrong
(4, 2, 4),   -- Succession: Lucy Prebble

(5, 3, 5),   -- The Umbrella Academy: Gerard Way

(6, 4, 6),   -- Dark: Baran bo Odar

(7, 5, 7),   -- The Witcher: Lauren Schmidt Hissrich

(8, 6, 8),   -- Westworld: Jonathan Nolan

(9, 7, 9),   -- Mindhunter: David Fincher

(10, 8, 10), -- Ozark: Jason Bateman

(11, 9, 11), -- Peaky Blinders: Steven Knight

(12, 10, 12),-- The Boys: Eric Kripke

(13, 11, 13),-- Fargo: Noah Hawley

(14, 12, 14),-- Narcos: Jose Padilha

(15, 13, 15),-- Black Mirror: Charlie Brooker

(16, 14, 16),-- Sherlock: Steven Moffat

(17, 15, 17),-- House of Cards: Beau Willimon

(18, 16, 18),-- Mr. Robot: Sam Esmail

(19, 17, 19),-- Luther: Neil Cross

(20, 18, 20),-- The Crown: Peter Morgan

(21, 19, 21),-- The Expanse: Naren Shankar

(22, 20, 22);-- Stranger Things: The Duffer Brothers

-- Link shows to actors
INSERT INTO shows_actors (id, show_id, actor_id) VALUES
(1, 1, 1),   -- Better Call Saul: Bob Odenkirk
(2, 1, 2),   -- Better Call Saul: Rhea Seehorn

(3, 2, 3),   -- Succession: Brian Cox
(4, 2, 4),   -- Succession: Jeremy Strong

(5, 3, 5),   -- The Umbrella Academy: Elliot Page
(6, 3, 6),   -- The Umbrella Academy: Tom Hopper

(7, 4, 7),   -- Dark: Louis Hofmann
(8, 4, 8),   -- Dark: Lisa Vicari

(9, 5, 9),   -- The Witcher: Henry Cavill
(10, 5, 10), -- The Witcher: Anya Chalotra

(11, 6, 11), -- Westworld: Evan Rachel Wood
(12, 6, 12), -- Westworld: Thandiwe Newton

(13, 7, 13), -- Mindhunter: Jonathan Groff
(14, 7, 14), -- Mindhunter: Holt McCallany

(15, 8, 15), -- Ozark: Jason Bateman
(16, 8, 16), -- Ozark: Laura Linney

(17, 9, 17), -- Peaky Blinders: Cillian Murphy
(18, 9, 18), -- Peaky Blinders: Paul Anderson

(19, 10, 19),-- The Boys: Karl Urban
(20, 10, 20),-- The Boys: Jack Quaid

(21, 11, 21),-- Fargo: Billy Bob Thornton
(22, 11, 22),-- Fargo: Martin Freeman

(23, 12, 23),-- Narcos: Wagner Moura
(24, 12, 24),-- Narcos: Pedro Pascal

(25, 13, 25),-- Black Mirror: Bryce Dallas Howard
(26, 13, 26),-- Black Mirror: Daniel Kaluuya

(27, 14, 27),-- Sherlock: Benedict Cumberbatch
(28, 14, 28),-- Sherlock: Martin Freeman

(29, 15, 29),-- House of Cards: Kevin Spacey
(30, 15, 30),-- House of Cards: Robin Wright

(31, 16, 31),-- Mr. Robot: Rami Malek
(32, 16, 32),-- Mr. Robot: Christian Slater

(33, 17, 33),-- Luther: Idris Elba
(34, 17, 34),-- Luther: Ruth Wilson

(35, 18, 35),-- The Crown: Claire Foy
(36, 18, 36),-- The Crown: Matt Smith

(37, 19, 37),-- The Expanse: Steven Strait
(38, 19, 38),-- The Expanse: Dominique Tipper

(39, 20, 39),-- Stranger Things: Millie Bobby Brown
(40, 20, 40);-- Stranger Things: Finn Wolfhard

-- Link shows to genres (sample)
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
(10, 5, 1), -- The Witcher: Drama

(11, 6, 5), -- Westworld: Sci-Fi
(12, 6, 7), -- Westworld: Thriller

(13, 7, 1), -- Mindhunter: Drama
(14, 7, 2), -- Mindhunter: Crime

(15, 8, 1), -- Ozark: Drama
(16, 8, 2), -- Ozark: Crime

(17, 9, 1), -- Peaky Blinders: Drama
(18, 9, 2), -- Peaky Blinders: Crime

(19, 10, 4), -- The Boys: Superhero
(20, 10, 1), -- The Boys: Drama

(21, 11, 1), -- Fargo: Drama
(22, 11, 2), -- Fargo: Crime

(23, 12, 1), -- Narcos: Drama
(24, 12, 2), -- Narcos: Crime

(25, 13, 5), -- Black Mirror: Sci-Fi
(26, 13, 7), -- Black Mirror: Thriller

(27, 14, 2), -- Sherlock: Crime
(28, 14, 1), -- Sherlock: Drama

(29, 15, 1), -- House of Cards: Drama
(30, 15, 2), -- House of Cards: Crime

(31, 16, 1), -- Mr. Robot: Drama
(32, 16, 7), -- Mr. Robot: Thriller

(33, 17, 2), -- Luther: Crime
(34, 17, 1), -- Luther: Drama

(35, 18, 1), -- The Crown: Drama
(36, 18, 7), -- The Crown: Thriller

(37, 19, 5), -- The Expanse: Sci-Fi
(38, 19, 7), -- The Expanse: Thriller

(39, 20, 5), -- Stranger Things: Sci-Fi
(40, 20, 7); -- Stranger Things: Thriller