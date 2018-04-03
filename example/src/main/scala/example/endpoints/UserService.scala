package example.endpoints

import cats.Monad
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

class UserService[F[_]: Monad] extends Http4sDsl[F] {
  val service: HttpService[F] = HttpService[F] {
    case GET -> Root / "users" / userId => NotFound(userId)
  }
}
