package com.jmarin.examples.validation

import com.jmarin.examples.validation.api.EitherValidator
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import com.jmarin.examples.validation.api.CatsValidation
import io.circe.syntax.*
import com.jmarin.examples.validation.serialization.JsonDecoders.given

object ValidationMain:

  def main(args: Array[String]): Unit =
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val now = LocalDateTime.now().format(formatter).toString()

    // Validation using Either?
    val registeredEitherUser = EitherValidator
      .validateRegistration(
        "jmarinotero",
        "MySuper$_SecretP@assword",
        "Juan",
        "Marin",
        25,
        "juan@email.com",
        "(111)-349-5555",
        "I live in this address",
        "20007",
        "Washington",
        "DC",
        now
      )
      .fold(
        e => s"Either Validation, Invalid user: $e.toString()}",
        a => a.asJson
      )

    println(registeredEitherUser)

    val invalidEitherUser = EitherValidator
      .validateRegistration(
        "jmarinotero",
        "password",
        "Juan",
        "Marin",
        25,
        "juanemail.com",
        "(111)-349-5555",
        "I live in this address",
        "207",
        "Washington",
        "Europa",
        now
      )
      .fold(
        e => s"Either Validation, Invalid user: $e.toString()}",
        a => a.asJson
      )
    println(invalidEitherUser)

    // A better approach, using ValidatedNel
    val registeredValidatedUser = CatsValidation
      .validateRegistration(
        "jmarinotero",
        "MySuper$_SecretP@assword",
        "Juan",
        "Marin",
        25,
        "juan@email.com",
        "(111)-349-5555",
        "I live in this address",
        "20007",
        "Washington",
        "DC",
        now
      )
      .fold(
        e => s"Cats Validation, Invalid user: $e.toString()}",
        a => a.asJson
      )

    println(registeredValidatedUser)

    val invalidValidateduser = CatsValidation
      .validateRegistration(
        "jmarinotero",
        "password",
        "Juan",
        "Marin",
        25,
        "juanemail.com",
        "(111)-349-5555",
        "I live in this address",
        "207",
        "Washington",
        "Europa",
        now
      )
      .fold(
        e => s"Cats Validation, Invalid user: $e.toString()}",
        a => a.asJson
      )
    println(invalidValidateduser)
