javacOptions ++= Seq("-encoding", "UTF-8")

lazy val root = (project in file(".")).
  settings(
    name := "physics",
    version := "0.2.0",
    scalaVersion := "2.12.1",
    coverageMinimum := 50,
    coverageFailOnMinimum := false,
    coverageHighlighting := true,
    publishArtifact in Test := false
  )


libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.1"
)

lazy val defaultSettings = Defaults.coreDefaultSettings ++ Seq(
  resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)

