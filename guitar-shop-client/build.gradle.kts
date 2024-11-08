plugins {
    alias(libs.plugins.apollo)
}

group = "org.jesperancinha.guitar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.apollo.runtime)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.create("prepareKotlinBuildScriptModel") {}

apollo {
    service("service") {
        packageName.set("org.jesperancinha.guitar.gui")
        introspection {
            endpointUrl.set("http://localhost:8080/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
    }
}