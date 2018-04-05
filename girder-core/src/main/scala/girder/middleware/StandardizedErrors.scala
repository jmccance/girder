package girder.middleware

import cats.Monad
import cats.data.Kleisli
import io.circe.generic.auto._
import io.circe.syntax._
import models.ErrorResponse
import org.http4s._
import org.http4s.circe._

object StandardizedErrors {
  def apply[F[_] : Monad](service: HttpService[F]): HttpService[F] =
    Kleisli { request =>
      service(request).semiflatMap { response =>
        response.withBody(
          ErrorResponse.from(request, response).asJson
        )
      }
    }
}
