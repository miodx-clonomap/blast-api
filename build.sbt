name         := "blast-api"
organization := "com.miodx.clonomap"
version      := "0.11.1"
description  := "A typesafe Scala BLAST API"
bucketSuffix := "era7.com"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.miodx.common"     %% "cosas"     % "0.10.1",
  "org.scalatest"        %% "scalatest" % "3.0.4" % Test,
  "com.github.tototoshi" %% "scala-csv" % "1.3.5" % Test
)
