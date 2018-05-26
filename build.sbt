import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(List(
    organization := "com.framex",
    scalaVersion := "2.12.3",
    version := "0.1.0-SNAPSHOT"
  )),
  name := "framex",
  libraryDependencies += scalaTest % Test,
  libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.6",
  libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.5",
  libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.3.0-M23"
)