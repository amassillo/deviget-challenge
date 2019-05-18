pre-requisites
1 - Have postgre installed on your machine (provided a docker file in any other case you want to use an image)
2 - create a postgre schema called minesweeper_db and a user identified by liquibase, password: th3runn1ng!us3r
3 - grant privileges to the user ex:
	CREATE USER liquibase WITH PASSWORD 'th3runn1ng!us3r';
	GRANT all privileges ON database minesweeper_db TO liquibase;
4 - mvn spring-boot:run or package and use docker

additional notes:
1 - used lombok
2 - used liquibase for db scripts versioning
3 - used maven to manage dependencies and generate projhect documentation w/ swagger
4 - used jpa + hibernate since I decided to go w/ postgres
5 - used mapstruct for entity/DTO mapping	


endpoints:
1 - new game

	POST /board/
	userId=
	cols=
	rows=
	mines=
	
2 - click cell
	
	POST /play/{boardId}	
	col=
	row=
	
3 - flag cell	
	