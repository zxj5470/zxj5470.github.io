import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	application
}

application {
	mainClassName = "MainKt"
}

val kotlin_version = rootProject.ext["kotlin_version"].toString()

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	implementation(kotlin("stdlib-jdk8", kotlin_version))
	implementation("com.github.HoshinoTented:HtmlDsl:1.0.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

getTasksByName("build", false).first().dependsOn("run")