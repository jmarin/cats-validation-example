import Dependencies._

lazy val scala3 = "3.2.0"
lazy val supportedScalaVersions = List(scala3)

ThisBuild / organization := "com.jmarin"
ThisBuild / scalaVersion := scala3

resolvers ++= repos

val sharedSettings = Seq(
  crossScalaVersions := supportedScalaVersions,
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % Version.scalaTest % "it, test",
    "org.scalacheck" %%% "scalacheck" % Version.scalaCheck % "it, test",
    "org.typelevel" %%% "cats-core" % Version.cats
  )
)

lazy val validation =
  crossProject(JSPlatform, JVMPlatform, NativePlatform)
    .crossType(CrossType.Full)
    .in(file("."))
    .configs(IntegrationTest)
    .settings(Defaults.itSettings ++ sharedSettings)
    .jvmSettings(
      // Configure JVM settings
    )
    .jsSettings(
      // Configure JS settings
    )
    .nativeSettings(
      // Configure Native settings
    )
