b: build-maven build-gradle
build-maven:
	mvn clean install
build-gradle:
	gradle wrapper
	./gradlew test build