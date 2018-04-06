package example

import cats.effect._
import cats.syntax.semigroupk._
import example.endpoints.{HelloService, UserService}
import girder.GirderApp
import girder.config.GirderConfig

class Components[F[_] : Effect] {
  private val userEndpoint = new UserService[F].service
  private val helloEndpoint = new HelloService[F].service

  private val config = pureconfig.loadConfigOrThrow[GirderConfig]("girder")

  val girder = new GirderApp[F](config)

  val httpServices = girder.middleware(
    helloEndpoint <+> userEndpoint
  )
}
