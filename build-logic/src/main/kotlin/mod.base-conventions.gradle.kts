plugins {
  `java-library`

  id("com.gradleup.shadow")
  id("io.papermc.paperweight.userdev")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

java {
  javaTarget(21)
  withSourcesJar()
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.spongepowered.org/maven/")
}

// **
// Paper Only
paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
// **

tasks {
  jar {
    archiveClassifier.set("dev")
  }

  // **
  // Paper Only
  shadowJar {
    archiveClassifier.set("")
  }

  build {
    dependsOn(shadowJar)
  }
  // **

  // **
  // Spigot Compatibility
  //reobfJar {
  //  remapperArgs.add("--mixin")
  //}
  //
  //build {
  //  dependsOn(reobfJar)
  //}
  // **
}
