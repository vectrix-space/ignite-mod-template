import com.diffplug.gradle.spotless.FormatExtension
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.regex.Pattern
import java.util.stream.Collectors

plugins {
  `java-library`
  id("com.diffplug.spotless")

  id("com.github.johnrengelman.shadow")
  id("io.papermc.paperweight.userdev")
}

// Expose version catalog
val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

java {
  javaTarget(17)
  withSourcesJar()
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.spongepowered.org/maven/")
}

dependencies {
  compileOnlyApi(libs.jetbrains.annotations)
}

spotless {
  fun FormatExtension.applyCommon() {
    trimTrailingWhitespace()
    endWithNewline()
    indentWithSpaces(2)
  }

  fun formatLicense(): String {
    val splitPattern = Pattern.compile("\r?\n")
    val lineSeparator = System.lineSeparator()
    val headerPrefix = "/*$lineSeparator"
    val linePrefix = " * "
    val headerSuffix = "$lineSeparator */"

    val headerText = String(Files.readAllBytes(rootProject.file("license_header.txt").toPath()), StandardCharsets.UTF_8)

    return splitPattern.splitAsStream(headerText)
      .map {
        StringBuilder(it.length + 4)
          .append(linePrefix)
          .append(it)
          .toString()
      }
      .collect(Collectors.joining(
        lineSeparator,
        headerPrefix,
        headerSuffix
      ))
  }


  java {
    licenseHeader(formatLicense())
    applyCommon()
  }

  kotlin {
    applyCommon()
  }
}

tasks {
  jar {
    archiveClassifier.set("dev")
  }

  reobfJar {
    remapperArgs.add("--mixin")
  }

  build {
    dependsOn(reobfJar)
  }
}
