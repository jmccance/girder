package girder

import cats.effect.Effect
import girder.middleware.{RequestIds, StandardizedErrors}
import org.http4s.HttpService
import org.http4s.server.HttpMiddleware
import org.http4s.server.middleware.Logger

class GirderApp[F[_]: Effect](services: (String, HttpService[F])*) {
  val middleware: HttpMiddleware[F] =
    (Logger[F](logHeaders = true, logBody = true) _
      andThen RequestIds[F]
      andThen StandardizedErrors[F])
}
