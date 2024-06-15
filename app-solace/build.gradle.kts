plugins {
    id("org.springframework.boot") apply true
}

dependencies {
    api(project(":mod-core"))
    api(project(":mod-solace"))
}

