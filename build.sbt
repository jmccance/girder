import girder.build._


lazy val root = project in file(".")

lazy val girder = project in file("girder")

lazy val example =
  (project in file("example"))
    .dependsOn(girder)
