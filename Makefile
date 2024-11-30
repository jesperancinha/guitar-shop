b: build-maven build-gradle
build-maven:
	mvn clean install
build-gradle:
	gradle wrapper
	./gradlew test build
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-gradle-update deps-plugins-update deps-java-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
