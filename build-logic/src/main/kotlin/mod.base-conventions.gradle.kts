import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("net.kyori.indra")
  id("net.kyori.indra.licenser.spotless")

  id("com.github.johnrengelman.shadow")
  id("io.papermc.paperweight.userdev")
}

configurations {
  val shadeApi = create("shadeApi")
  val shadeImplementation = create("shadeImplementation")

  api { extendsFrom(shadeApi) }
  implementation { extendsFrom(shadeImplementation) }
}

repositories {
  mavenCentral()
  maven {
    url = uri("https://oss.sonatype.org/content/groups/public/")
  }
  maven {
    url = uri("https://repo.papermc.io/repository/maven-public/")
  }
  maven {
    url = uri("https://repo.spongepowered.org/maven/")
  }
}

indra {
  javaVersions {
    target(17)
  }

  mitLicense()
}

val jar = tasks.named<Jar>("jar")

val shadowJar = tasks.named<ShadowJar>("shadowJar") {
  configurations = listOf(project.configurations.named("shadeApi").get(), project.configurations.named("shadeImplementation").get())

  from(jar)
}

tasks {
  build {
    dependsOn(reobfJar)
  }

  reobfJar {
    remapperArgs.add("--mixin")
  }
}

artifacts {
  archives(shadowJar)
}
