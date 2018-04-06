package girder.config

case class GirderConfig(
  cors: CorsConfig
)

case class CorsConfig(allowedOrigins: List[String])
