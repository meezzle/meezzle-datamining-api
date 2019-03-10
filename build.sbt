name := """meezzle-datamining"""

lazy val root = (project in file("."))

version := "1.0"

scalaVersion := "2.12.7"
 
scalacOptions in (Compile,run) ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3",
  "com.typesafe.akka" %% "akka-http"   % "10.1.7",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
)