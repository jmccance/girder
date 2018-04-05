import girder.build._

name := "girder"

lazy val `girder-core` = project in file("girder-core")

lazy val example =
  (project in file("example"))
    .dependsOn(`girder-core`)
