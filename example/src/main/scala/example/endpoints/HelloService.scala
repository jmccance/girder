package example.endpoints

import cats.Monad
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class HelloService[F[_] : Monad] extends Http4sDsl[F] {
  val service = HttpService[F] {
    case GET -> Root / "hello" / name =>
      Ok(Json.obj("message" -> Json.fromString(s"Hello, $name")))
  }
}
