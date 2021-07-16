plugins {
    java
    id("io.github.patrick.remapper") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("io.papermc.paper:paper:1.17-R0.1-SNAPSHOT")
    compileOnly(project(":API"))
    mojangMapping("org.spigotmc:minecraft-server:1.17-R0.1-SNAPSHOT:maps-mojang@txt")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}