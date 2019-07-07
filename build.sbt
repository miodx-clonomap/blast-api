name          := "blast-api"
organization  := "com.miodx.clonomap"
version       := "0.11.0-SNAPSHOT"
description   := "A typesafe Scala BLAST API"
scalaVersion  := "2.11.11"
bucketSuffix  := "miodx.com"

libraryDependencies ++= Seq(
  "com.miodx.common"      %% "cosas"        % "0.10.1",
  "com.github.tototoshi"  %% "scala-csv"    % "1.3.4" % Test
)

// incOptions := incOptions.value.withNameHashing(false)

wartremoverErrors in (Test,    compile) := Seq()
