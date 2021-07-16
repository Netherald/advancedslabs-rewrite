plugins {
    java
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.inventivetalent.org/content/groups/public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.codemc.org/repository/maven-public")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}