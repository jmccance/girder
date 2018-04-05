package girder.models

import scala.collection.immutable._

case class Page[A](content: Seq[A])
