name := """tax-calculator"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

coverageExcludedPackages := "<empty>;Reverse.*;filters.*;router.*"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe.play" %% "play-json" % "2.5.19",
  "org.scalatest"               %%  "scalatest"           % "3.0.1"           % Test,
  "org.scalatestplus.play"      %%  "scalatestplus-play"  % "2.0.0"           % Test
)

