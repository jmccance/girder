package girder.build

import sbt._
import sbt.Keys._


object GirderProjectPlugin extends AutoPlugin {
  override def trigger = AllRequirements

  override lazy val projectSettings =
    Seq(
      scalaVersion := "2.12.4",
      scalacOptions ++= Seq(
        "-feature",
        "-language:higherKinds",
        "-Ypartial-unification",
      )
    )
}
