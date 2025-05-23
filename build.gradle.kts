plugins {
	java
	kotlin("jvm") version "2.2.0-RC"
	id("org.springframework.boot") version "3.5.0" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
	id("com.github.ben-manes.versions") version "0.52.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

version = "0"

subprojects {

	group = "symetricum"

	apply {
		plugin("java")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("io.spring.dependency-management")
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")

		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("io.projectreactor:reactor-test")

		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
