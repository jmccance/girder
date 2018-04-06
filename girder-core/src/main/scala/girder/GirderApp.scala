package girder

import cats.effect.Effect
import girder.config.GirderConfig
import girder.middleware.{IncludeRequestIds, StandardizeErrors}
import org.http4s.server.HttpMiddleware
import org.http4s.server.middleware.{CORS, CORSConfig, Logger}

class GirderApp[F[_] : Effect](config: GirderConfig) {

  val corsConfig: CORSConfig = CORS.DefaultCORSConfig.copy(
    anyOrigin = config.cors.allowedOrigins.isEmpty,
    allowedOrigins = config.cors.allowedOrigins.contains
  )

  val middleware: HttpMiddleware[F] =
    (IncludeRequestIds[F] _
      andThen (CORS[F](_, corsConfig))
      andThen StandardizeErrors[F]
      andThen Logger[F](logHeaders = true, logBody = true))
}
