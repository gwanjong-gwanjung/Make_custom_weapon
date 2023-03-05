plugins {
    idea
    kotlin("jvm") version "1.8.0"

}

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT") //해당되는 버전을 입력
    implementation("io.github.monun:kommand-api:3.1.2")
    implementation("io.github.monun:invfx-api:3.3.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.jar {
    destinationDirectory.set(file("S:\\플러그인 테스트 서버\\plugins"))
}
