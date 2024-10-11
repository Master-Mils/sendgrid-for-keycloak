.PHONY: install package test test-integ test-docker update-deps clean

VERSION := $(shell mvn help:evaluate -Dexpression=project.version --batch-mode | grep -e '^[^\[]')
install:
	@java -version || (echo "Java is not installed, please install Java >= 7"; exit 1);
	mvn clean install -DskipTests=true -Dgpg.skip -Dmaven.javadoc.skip=true -B
	cp target/sendgrid-java-for-keycloak-$(VERSION)-shaded.jar sendgrid-java-for-keycloak.jar

deploy-file:
	mvn deploy:deploy-file -DgroupId=com.mastermils.sendgrid -DartifactId=sendgrid-java-for-keycloak -Dversion=4.10.2 -DupdateReleaseInfo=true -Dfile=sendgrid-java-for-keycloak-4.10.2.jar

deploy:
	mvn deploy -e -DskipTests=true -Dtoken=$(GITHUB_TOKEN)

package:
	mvn package -DskipTests=true -Dgpg.skip -Dmaven.javadoc.skip=true -B
	cp target/sendgrid-java-for-keycloak-$(VERSION)-shaded.jar sendgrid-java-for-keycloak.jar

test:
	mvn test spotbugs:spotbugs checkstyle:check -Dcheckstyle.config.location=google_checks.xml

test-integ: test

version ?= latest
test-docker:
	curl -s https://raw.githubusercontent.com/sendgrid/sendgrid-oai/HEAD/prism/prism-java.sh -o prism.sh
	version=$(version) bash ./prism.sh

update-deps:
	mvn versions:use-latest-releases versions:commit -DallowMajorUpdates=false

clean:
	mvn clean
