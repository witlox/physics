javacOptions ++= Seq("-encoding", "UTF-8")

lazy val root = (project in file(".")).
  settings(
    name := "physics",
    version := "1.0",
    scalaVersion := "2.12.1"
  )


libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.1"
)

lazy val defaultSettings = Defaults.coreDefaultSettings ++ Seq(
  resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)
