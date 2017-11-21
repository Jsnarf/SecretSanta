name := "SecretSanta"

version := "0.1"

scalaVersion := "2.12.1"

resolvers += "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven"

libraryDependencies ++= Seq(
  "ch.lightshed" %% "courier" % "0.1.4",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0",
  "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.8.2" % Runtime,
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % Test,
  "org.mockito" % "mockito-all" % "1.9.5" % Test
)
