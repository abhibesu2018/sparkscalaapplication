val ScalatraVersion = "2.6.3"

organization := "com.abhi"

name := "SparkScalaApplication"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.6"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.apache.spark" %% "spark-mllib" % "2.3.0",
  "org.apache.spark" %% "spark-streaming" % "2.3.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
