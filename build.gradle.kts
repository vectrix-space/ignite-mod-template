plugins {
  id("mod.base-conventions")
}

dependencies {
  compileOnly(libs.ignite)
  compileOnly(libs.mixin)

  paperweight.paperDevBundle(libs.versions.paper)
}
