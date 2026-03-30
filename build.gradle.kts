plugins {
    kotlin("jvm") version "2.3.0"
    application
}

group = "com.closedbrain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.hypfvieh:dbus-java-core:5.2.0")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "com.closedbrain.MainKt"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}