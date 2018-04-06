libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
