package example

import cats.effect._
import cats.syntax.semigroupk._
import example.endpoints.{HelloService, UserService}
import girder.GirderApp

class Components[F[_] : Effect] {
  private val userEndpoint = new UserService[F].service
  private val helloEndpoint = new HelloService[F].service

  val girder = new GirderApp[F]

  val httpServices = girder.middleware(
    helloEndpoint <+> userEndpoint
  )
}
