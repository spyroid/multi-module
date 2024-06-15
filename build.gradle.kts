plugins {
	java
	kotlin("jvm") version "2.0.0"
	id("org.springframework.boot") version "3.3.0" apply false
	id("io.spring.dependency-management") version "1.1.5" apply false
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
