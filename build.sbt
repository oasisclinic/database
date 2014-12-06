import play.PlayJava

name := "oasis-surveyr"

version := "1.0"

scalaVersion := "2.11.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  filters,
  javaWs,
  "uk.co.panaxiom" %% "play-jongo" % "0.7.1-jongo1.0",
  "com.wordnik" %% "swagger-play2" % "1.3.10",
  "org.apache.shiro" % "shiro-core" % "1.2.3"
)

sources in (Compile,doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false