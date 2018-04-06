package example

import cats.effect._
import example.endpoints.{HelloService, Post, PostService, UserService}
import girder.GirderApp
import girder.config.GirderConfig
import org.http4s.EntityDecoder
import org.http4s.circe._
import org.http4s.server.Router

class Components[F[_] : Effect] {
  implicit val postEncoder: EntityDecoder[F, Post] = jsonOf[F, Post]
  private val userEndpoint = new UserService[F].service
  private val helloEndpoint = new HelloService[F].service
  private val postsEndpoint = new PostService[F].service

  private val config = pureconfig.loadConfigOrThrow[GirderConfig]("girder")

  val girder = new GirderApp[F](config)

  val httpServices = girder.middleware(
    Router(
      "/users" -> userEndpoint,
      "/hello" -> helloEndpoint,
      "/posts" -> postsEndpoint
    )
  )
}
