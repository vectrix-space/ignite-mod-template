plugins {
  id("mod.base-conventions")
}

dependencies {
  paperweight.paperDevBundle(libs.versions.paper)

  compileOnly(libs.ignite)
  compileOnly(libs.mixin)
  compileOnly(libs.mixinExtras)

  annotationProcessor(libs.mixinExtras)
}
