package girder

import cats.effect.Effect
import girder.middleware.{IncludeRequestIds, StandardizeErrors}
import org.http4s.HttpService
import org.http4s.server.HttpMiddleware
import org.http4s.server.middleware.Logger

class GirderApp[F[_] : Effect](services: (String, HttpService[F])*) {
  val middleware: HttpMiddleware[F] =
    (IncludeRequestIds[F] _
      andThen StandardizeErrors[F]
      andThen Logger[F](logHeaders = true, logBody = true))
}
