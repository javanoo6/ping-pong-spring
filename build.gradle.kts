import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("junit:junit:4.13.1")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-json
    implementation("org.springframework.boot:spring-boot-starter-json:2.7.3")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.vertx:vertx-mongo-client:4.3.3")
// https://mvnrepository.com/artifact/io.kotest/kotest-runner-junit5-jvm
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.4.2")

    testImplementation("io.mockk:mockk:1.9.3")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
