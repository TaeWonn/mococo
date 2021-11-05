import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.5.31"
    val springBootVersion = "2.5.6"

    id("org.springframework.boot") version "$springBootVersion"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.0-RC"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.6.0-RC"
    kotlin("jvm") version "$kotlinVersion"
    kotlin("plugin.spring") version "$kotlinVersion"
    kotlin("plugin.jpa") version "$kotlinVersion"
    kotlin("kapt") version "$kotlinVersion"

    idea
}

group = "kr.side"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

// QueryDSL QEntity Build Path
idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

dependencies {
    val queryDslVersion = "5.0.0"

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging:1.12.5")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.mockk:mockk:1.10.0")

    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.2")
    //query dsl
    compileOnly("com.querydsl:querydsl-jpa:${queryDslVersion}")
    kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jpa")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
