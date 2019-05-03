name          := "blast-api"
organization  := "com.miodx.clonomap"
version       := "0.10.0"
description   := "A typesafe Scala BLAST API"
scalaVersion  := "2.11.11"
bucketSuffix  := "era7.com"

libraryDependencies ++= Seq(
  "com.miodx.common"      %% "cosas"        % "0.10.1",
  "com.github.tototoshi"  %% "scala-csv"    % "1.3.4" % Test
)

// incOptions := incOptions.value.withNameHashing(false)

wartremoverErrors in (Test,    compile) := Seq()
