plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  maven {
    url = uri("https://repo.papermc.io/repository/maven-public/")
  }
}

dependencies {
  val indraVersion = "3.0.1"
  implementation("net.kyori", "indra-common", indraVersion)
  implementation("net.kyori", "indra-licenser-spotless", indraVersion)
  implementation("gradle.plugin.com.github.johnrengelman", "shadow", "7.1.2")
  implementation("io.papermc.paperweight.userdev", "io.papermc.paperweight.userdev.gradle.plugin", "1.5.0")
}
