ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.0"

lazy val root = (project in file("."))
  .settings(
    name := "Exercises",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    testFrameworks += new TestFramework("org.scalatest.tools.Framework")
  )