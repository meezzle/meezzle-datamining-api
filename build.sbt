name := """meezzle-datamining"""

lazy val root = (project in file("."))

version := "1.0"

scalaVersion := "2.12.7"
 
scalacOptions in (Compile,run) ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3",
  "com.typesafe.akka" %% "akka-http"   % "10.1.7",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7",
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "1.0-M3",
  "com.lightbend.akka" %% "akka-stream-alpakka-orientdb" % "1.0-RC1",
  "org.jsoup" % "jsoup" % "1.11.3",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.9.8",
  "org.postgresql" % "postgresql" % "42.2.5"

)