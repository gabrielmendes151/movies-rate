COPY movies (movie_year, title, studios, producers, winner)
 FROM 'src/test/resources/data/movielist.csv'
 DELIMITER ';'
 CSV HEADER;