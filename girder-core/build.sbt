val cats = {
  val version = "1.1.0"

  Seq(
    "org.typelevel" %% "cats-core" % version,
    "org.typelevel" %% "cats-kernel" % version,
    "org.typelevel" %% "cats-macros" % version
  )
}

val circe = {
  val version = "0.9.3"

  Seq(
    "io.circe" %% "circe-core" % version,
    "io.circe" %% "circe-generic" % version,
  )
}

val http4s = {
  val version = "0.18.4"

  Seq(
    "org.http4s" %% "http4s-blaze-server" % version,
    "org.http4s" %% "http4s-circe" % version,
    "org.http4s" %% "http4s-dsl" % version,
  )
}

libraryDependencies ++=
  (cats
    ++ circe
    ++ http4s
    ++ Seq(
    "com.github.pureconfig" %% "pureconfig" % "0.9.1",
  ))

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)