package girder.middleware

import cats.Functor
import cats.data.Kleisli
import org.http4s.{AttributeKey, Header, HttpService}

object IncludeRequestIds {

  val GirderRequestId: AttributeKey[String] = AttributeKey[String]

  def apply[F[_] : Functor](service: HttpService[F]): HttpService[F] =
    Kleisli { request =>
      // TODO Convert to Stream[F, Id]
      // Surely there's some way to use a Stream of ids instead of doing the random generation here,
      // but I'm not sure how to compose F and then Stream together zip-wise.
      val requestId = java.util.UUID.randomUUID().toString

      service(
        request.withAttribute(GirderRequestId(requestId))
      ).map { response =>
        response.putHeaders(Header("girder-request-id", requestId))
      }
    }
}
