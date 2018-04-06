package girder.middleware

import cats.data.{Kleisli, OptionT}
import cats.effect.Sync
import cats.syntax.applicative._
import cats.syntax.flatMap._
import girder.models.ErrorResponse
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.headers._

object StandardizeErrors {
  def apply[F[_] : Sync](service: HttpService[F]): HttpService[F] =
    Kleisli { request =>
      service(request).semiflatMap { response =>
        getDetails(response).flatMap { details =>
          response.withBody(
            ErrorResponse(
              method = request.method.renderString,
              path = request.pathInfo,
              status = response.status.code,
              details = Some(details)
            ).asJson
          )
        }
      }
    }

  private[this] def getDetails[F[_] : Sync](response: Response[F]): F[Json] = {
    response.contentType.collect {
      case `Content-Type`(MediaType.`application/json`, _) =>
        response.attemptAs[Json].fold(
          _ => Json.obj(),
          identity
        )

      case `Content-Type`(MediaType.`text/plain`, _) =>
        response.attemptAs[String].fold(
          _ => "".asJson,
          _.asJson
        )
    }.getOrElse(Json.obj("message" -> response.status.renderString.asJson).pure)
  }
}
