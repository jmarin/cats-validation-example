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
    "org.typelevel" %%% "cats-core" % Version.cats,
    "io.circe" %%% "circe-core" % Version.circe,
    "io.circe" %%% "circe-generic" % Version.circe,
    "io.circe" %%% "circe-parser" % Version.circe
  )
)

lazy val validation =
  crossProject(JSPlatform, JVMPlatform, NativePlatform)
    .crossType(CrossType.Full)
    .in(file("."))
    .enablePlugins(JavaAppPackaging)
    .configs(IntegrationTest)
    .settings(Defaults.itSettings ++ sharedSettings)
    .jvmSettings(
      // Configure JVM settings
    )
    .jsSettings(
      // Configure JS settings
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq(
        "io.github.cquiroz" %%% "scala-java-time" % "2.3.0",
        "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.3.0"
      )
    )
    .nativeSettings(
      // Configure Native settings
    )
