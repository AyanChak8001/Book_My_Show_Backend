-- P1_SQL.sql
-- Schema + sample data for BookMyShow (MySQL)
-- Run this in MySQL Workbench (select your database `bookmyshow_db` first)

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS movie_show;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS screen;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS app_user;

SET FOREIGN_KEY_CHECKS = 1;

-- Movie
CREATE TABLE movie (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  language VARCHAR(50),
  duration_mins INT,
  genre VARCHAR(100),
  certification VARCHAR(20)
);

-- Theatre
CREATE TABLE theater (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  city VARCHAR(100),
  state VARCHAR(100),
  pincode VARCHAR(20)
);

-- Screen
CREATE TABLE screen (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  seat_capacity INT,
  theatre_id BIGINT,
  CONSTRAINT fk_screen_theatre FOREIGN KEY (theatre_id) REFERENCES theater(id)
);

-- Seat
CREATE TABLE seat (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  row_label VARCHAR(10),
  seat_number INT,
  seat_type VARCHAR(30),
  screen_id BIGINT,
  CONSTRAINT fk_seat_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
);

-- Movie Show (you named it movie_show)
CREATE TABLE movie_show (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  show_date DATE,
  start_time TIME,
  end_time TIME,
  ticket_price DECIMAL(10,2),
  format VARCHAR(50),
  language VARCHAR(50),
  movie_id BIGINT,
  screen_id BIGINT,
  CONSTRAINT fk_show_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
  CONSTRAINT fk_show_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
);

-- App User
CREATE TABLE app_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  phone VARCHAR(50)
);

-- Booking
CREATE TABLE booking (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  total_amount DECIMAL(10,2),
  status ENUM('PENDING','CONFIRMED','CANCELLED') DEFAULT 'PENDING',
  user_id BIGINT,
  show_id BIGINT,
  CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES app_user(id),
  CONSTRAINT fk_booking_show FOREIGN KEY (show_id) REFERENCES movie_show(id)
);

-- Ticket
CREATE TABLE ticket (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  price DECIMAL(10,2),
  booking_id BIGINT,
  seat_id BIGINT,
  CONSTRAINT fk_ticket_booking FOREIGN KEY (booking_id) REFERENCES booking(id),
  CONSTRAINT fk_ticket_seat FOREIGN KEY (seat_id) REFERENCES seat(id)
);

-- ======================
-- Sample data (seed)
-- ======================

-- Movies
INSERT INTO movie (title, language, duration_mins, genre, certification)
VALUES
('Inception', 'English', 148, 'Sci-Fi', 'U/A'),
('Interstellar', 'English', 169, 'Sci-Fi', 'U/A'),
('3 Idiots', 'Hindi', 170, 'Comedy/Drama', 'U');

-- Theatres
INSERT INTO theater (name, address, city, state, pincode)
VALUES
('PVR Cinemas - Oberoi Mall', 'Linking Rd', 'Mumbai', 'Maharashtra', '400056'),
('INOX Mall', 'MG Road', 'Bengaluru', 'Karnataka', '560001');

-- Screens
INSERT INTO screen (name, seat_capacity, theatre_id)
VALUES
('Screen 1', 120, 1),
('Screen 2', 150, 1),
('Screen A', 100, 2);

-- Seats (a few sample seats)
INSERT INTO seat (row_label, seat_number, seat_type, screen_id)
VALUES
('A', 1, 'REGULAR', 1),
('A', 2, 'REGULAR', 1),
('A', 3, 'PREMIUM', 1),
('B', 1, 'REGULAR', 1),
('A', 1, 'REGULAR', 2),
('A', 2, 'REGULAR', 2);

-- Movie shows
INSERT INTO movie_show (show_date, start_time, end_time, ticket_price, format, language, movie_id, screen_id)
VALUES
('2025-09-25', '10:00:00', '12:30:00', 200.00, '2D', 'English', 1, 1),
('2025-09-25', '14:00:00', '16:30:00', 250.00, '2D', 'English', 1, 1),
('2025-09-25', '18:00:00', '20:30:00', 300.00, 'IMAX', 'English', 2, 2);

-- Users
INSERT INTO app_user (full_name, email, phone)
VALUES
('Alice Johnson', 'alice@example.com', '9876543210'),
('Rahul Kumar', 'rahul@example.com', '9123456780');

-- Bookings
INSERT INTO booking (total_amount, status, user_id, show_id)
VALUES
(600.00, 'CONFIRMED', 1, 2),
(300.00, 'PENDING', 2, 3);

-- Tickets (two tickets for booking 1)
INSERT INTO ticket (price, booking_id, seat_id)
VALUES
(300.00, 1, 1),
(300.00, 1, 2),
(150.00, 2, 5);

-- done
