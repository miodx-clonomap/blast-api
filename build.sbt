name         := "blast-api"
organization := "ohnosequences"
version      := "0.11.1"
description  := "A typesafe Scala BLAST API"
bucketSuffix := "era7.com"

crossScalaVersions := Seq("2.11.11", "2.12.3")
scalaVersion := crossScalaVersions.value.max

libraryDependencies ++= Seq(
  "com.miodx.common"     %% "cosas"     % "0.10.1",
  "org.scalatest"        %% "scalatest" % "3.0.4" % Test,
  "com.github.tototoshi" %% "scala-csv" % "1.3.5" % Test
)
