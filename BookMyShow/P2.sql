-- P2_SQL.sql
-- Query: list all shows on a given date at a given theatre with timings

-- Example with hard-coded theatre id and date:
SELECT
  ms.id AS show_id,
  m.title AS movie_title,
  sc.name AS screen_name,
  ms.show_date,
  ms.start_time,
  ms.end_time,
  ms.ticket_price,
  ms.format,
  ms.language
FROM movie_show ms
JOIN movie m ON ms.movie_id = m.id
JOIN screen sc ON ms.screen_id = sc.id
JOIN theater t ON sc.theatre_id = t.id
WHERE t.id = 1
  AND ms.show_date = '2025-09-25'
ORDER BY ms.start_time;

-- Parameterized style (useful in MySQL Workbench: set variables before running)
-- SET @theatre_id = 1;
-- SET @show_date  = '2025-09-25';
-- SELECT ...
-- WHERE t.id = @theatre_id AND ms.show_date = @show_date;
