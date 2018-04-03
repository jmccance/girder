package example

import cats.effect.IO
import fs2.StreamApp
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global

object ExampleApp extends StreamApp[IO] with Http4sDsl[IO] {
  override def stream(args: List[String], requestShutdown: IO[Unit]) = {
    val components = new Components[IO]

    BlazeBuilder[IO]
      .mountService(components.httpServices, "/")
      .serve
  }
}
