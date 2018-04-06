package example.endpoints

import cats.effect._
import cats.syntax.flatMap._
import cats.syntax.functor._
import io.circe.generic.JsonCodec
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

@JsonCodec case class Post(body: String)

class PostService[F[_] : Sync](implicit decoder: EntityDecoder[F, Post]) extends Http4sDsl[F] {
  private[this] var posts: List[Post] = Nil

  def service = HttpService[F] {
    case request@POST -> Root =>
      for {
        post <- request.as[Post]
        resp <- Ok(post.asJson)
      } yield resp
  }
}
