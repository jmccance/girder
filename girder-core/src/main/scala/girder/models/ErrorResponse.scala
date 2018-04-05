package girder.models

import io.circe.Encoder
import io.circe.generic.JsonCodec
import io.circe.syntax._

@JsonCodec case class ErrorResponse(
  method: String,
  path: String,
  status: Int,
  details: Option[io.circe.Json]
) {

  def withDetails[A: Encoder](details: A): ErrorResponse =
    this.copy(details = Some(details.asJson))

}
