lazy val `girder` =
  (project in file("."))
    .aggregate(
      `girder-core`,
      example
    )

lazy val `girder-core` = project in file("girder-core")

lazy val example =
  (project in file("example"))
    .dependsOn(`girder-core`)
