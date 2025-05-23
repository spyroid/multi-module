plugins {
    id("org.springframework.boot") apply true
}

dependencies {
//    implementation("org.springframework.grpc:spring-grpc-dependencies:0.8.0")
    implementation(project(":orange-box-api"))
}

//dependencyManagement {
//    imports {
//        mavenBom("org.springframework.grpc:spring-grpc-dependencies:0.8.0")
//    }
//}

tasks {
    jar {
        enabled = false
    }
}