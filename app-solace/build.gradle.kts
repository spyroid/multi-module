plugins {
    id("org.springframework.boot") apply true
}

dependencies {
    implementation(project(":starter"))
    implementation(project(":mod-core"))
    implementation(project(":mod-solace"))
    implementation(project(":orange-box-api"))
}

