import com.google.protobuf.gradle.id

plugins {
    id("org.springframework.boot") apply true
    id("com.google.protobuf") version "0.9.5"
}

dependencies {
    api(platform("org.springframework.grpc:spring-grpc-build-dependencies:0.8.0"))
    api("io.grpc:grpc-services")
    api("org.springframework.grpc:spring-grpc-spring-boot-starter")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-build-dependencies:0.8.0")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
    }
}

tasks {
    bootJar {
        enabled = false
    }
    jar {
        archiveClassifier.set("")
    }
}