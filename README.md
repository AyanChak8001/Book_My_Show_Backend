# 🎬 BookMyShow Clone - Ticket Booking Platform

This project is a simplified clone of **BookMyShow**, built with **Spring Boot** and **MySQL**.  
It allows users to browse movies, check theatres and shows, book tickets, and manage bookings.

---

## 📌 Assignment Requirements

### P1
- List all entities, their attributes, and table structures.
- SQL queries required to create these tables with a few sample entries.
- Ensure tables follow **1NF, 2NF, 3NF, and BCNF**.

### P2
- Write a query to list down all shows on a given date at a given theatre along with their show timings.

---

## 📂 Project Structure



BookMyShow/
├── src/main/java/com/example/bookmyshow/
│    ├── controller/    # REST controllers
│    ├── dto/           # DTOs
│    ├── entity/        # Entities (JPA)
│    ├── mapper/        # Mapper classes
│    ├── repository/    # Spring Data Repositories
│    └── service/       # Service layer
├── src/main/resources/
│    └── application.properties
├── p1.sql
├── p2.sql
├── README.md
└── pom.xml

Create database in MySQL:
        CREATE DATABASE bookmyshow_db;
Import schema and sample data:
        mysql -u root -p bookmyshow_db < p1.sql
Run the Spring Boot application:
        mvn spring-boot:run