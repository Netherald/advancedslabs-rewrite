plugins {
    java
    id("io.freefair.lombok") version "6.0.0-m2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    //maven("https://repo.inventivetalent.org/content/groups/public/")
}

dependencies {
    implementation(project(":API"))
    implementation(project(":v1_17_R1"))
    implementation(project(":v1_17_R2"))

    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper:1.17-R0.1-SNAPSHOT")

    implementation("org.bstats:bstats-bukkit:2.2.1")
    implementation("org.inventivetalent:reflectionhelper:1.18.4-SNAPSHOT")
    implementation("org.inventivetalent:messagebuilder:1.1.0-SNAPSHOT")
    implementation("org.inventivetalent:apimanager:1.0.5-SNAPSHOT")

    implementation("org.inventivetalent.packetlistenerapi:api:3.7.5-SNAPSHOT")

    implementation("org.inventivetalent:glowapi:1.4.20-SNAPSHOT")
    implementation("org.inventivetalent:boundingboxapi:1.3.6-SNAPSHOT")
    implementation("org.inventivetalent.spiget-update:bukkit:1.4.3-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {

    javadoc {
        options.encoding = "UTF-8"
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    jar {
        from (shade.map { if (it.isDirectory) it else zipTree(it) })
    }
}