package com.jmarin.examples.validation.serialization

import io.circe.*
import io.circe.generic.semiauto.*
import com.jmarin.examples.validation.model.RegisteredUser

object JsonDecoders:
  given Encoder[RegisteredUser] = deriveEncoder[RegisteredUser]
  given Decoder[RegisteredUser] = deriveDecoder[RegisteredUser]
