pre-requisites

1 - java 8+ Have postgre installed on your machine (provided a docker file in any other case you want to use an image)

2 - create a postgre schema called minesweeper_db and a user identified by liquibase, password: {your selected password}

3 - grant privileges to the user ex:

	CREATE USER liquibase WITH PASSWORD  {your selected password};
	GRANT all privileges ON database minesweeper_db TO liquibase;
	
4 - mvn clean package (to generate mapstruct classes)
	mvn spring-boot:run or  use docker w/ packaged jar

additional notes:

1 - used lombok
2 - used liquibase for db scripts versioning
3 - used maven to manage dependencies and generate project documentation w/ swagger
4 - used jpa + hibernate since I decided to go w/ postgre
5 - used mapstruct for entity/DTO mapping	
6 - used factory pattern to generate board
7 - use command pattern to handle click interactions
8 - did not add auth, just sent user_id in the service which checks board belongs to the user. 
	auth can be added and user_id can be obtained from the auth token in the request's header


endpoints (see swagger documentation):
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
	userId=
	
3 - flag cell
	
	POST /play/{boardId}/flag	
	col=
	row=
	flag= of of these two (QUESTION_MARK, FLAG)
	userId=

4- get board (prints board)
	
	GET /board/{boardId}/	
	userId=	
