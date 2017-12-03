"# tracker2" 

rebuild:
	mvn package
rebuild without tests:
	mvn package -am -o -DskipTests -T 1C
	
create POST request via curl:

	curl --data "hash=-442696469&date=&time=&duration=" localhost:8080/postIter
	
and via json object with params:
    
    String hash;
    String date;
    String time;
    String duration;
	
in development (maybe):	
	
	curl --data "hash=-442696469&duration=00:25:00" localhost:8080/postIter	
	
	curl --data "hash=-442696469" localhost:8080/postIter	
	
	
	curl --data "hash=-442696469&date=2017-11-20&time=00:15:44" localhost:8080/postIter	
	
	
	
run using cmd:
	mvnw spring-boot:run
	
	