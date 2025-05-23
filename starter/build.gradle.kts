plugins {
    id("org.springframework.boot") apply true
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}

tasks {
    bootJar {
        enabled = false
    }
}