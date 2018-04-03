package models

import io.circe.generic.JsonCodec
import io.circe.generic.semiauto._
import org.http4s.{Request, Response}

@JsonCodec case class ErrorResponse[T](
  method: String,
  path: String,
  status: Int,
  details: Option[T]
) {
  def withDetails(details: T): ErrorResponse[T] = this.copy(details = Some(details))
}

object ErrorResponse {
  def from[F[_], Nothing](request: Request[F], response: Response[F]): ErrorResponse[Nothing] = {
    ErrorResponse(
      method = request.method.renderString,
      path = request.pathInfo,
      status = response.status.code,
      details = None
    )
  }
}
