package com.jmarin.examples.validation.api

import com.jmarin.examples.validation.model.RegistrationError
import com.jmarin.examples.validation.model.InvalidUserName
import com.jmarin.examples.validation.model.InvalidFirstName
import com.jmarin.examples.validation.model.InvalidPassword
import com.jmarin.examples.validation.model.InvalidAge
import com.jmarin.examples.validation.model.InvalidEmail
import com.jmarin.examples.validation.model.InvalidPhoneNumber
import com.jmarin.examples.validation.model.InvalidZipCode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try
import cats.data.EitherT
import cats.implicits.catsStdInstancesForOption
import com.jmarin.examples.validation.model.InvalidCreatedDate
import com.jmarin.examples.validation.model.Geography.*
import com.jmarin.examples.validation.model.InvalidState
import com.jmarin.examples.validation.model.InvalidCity
import com.jmarin.examples.validation.model.RegisteredUser
import com.jmarin.examples.validation.model.InvalidLastName
import com.jmarin.examples.validation.model.InvalidAddress

trait EitherValidator:

  private def validateUsername(
      userName: String
  ): Either[RegistrationError, String] =
    Either.cond(userName.matches("^[a-zA-Z0-9]+$"), userName, InvalidUserName)

  private def validatePassword(
      password: String
  ): Either[RegistrationError, String] =
    Either.cond(
      password.matches(
        "(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
      ),
      password,
      InvalidPassword
    )

  private def validateFirstName(
      firstName: String
  ): Either[RegistrationError, String] =
    Either.cond(
      firstName.matches("^[a-zA-Z]+$"),
      firstName,
      InvalidFirstName
    )

  private def validateLastName(
      firstName: String
  ): Either[RegistrationError, String] =
    Either.cond(
      firstName.matches("^[a-zA-Z]+$"),
      firstName,
      InvalidLastName
    )

  private def validateAge(age: Int): Either[RegistrationError, Int] =
    Either.cond(
      age >= 18 && age <= 99,
      age,
      InvalidAge
    )

  private def validateEmail(email: String): Either[RegistrationError, String] =
    Either.cond(
      email.matches(
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
      ),
      email,
      InvalidEmail
    )

  private def validatePhone(
      phoneNumber: String
  ): Either[RegistrationError, String] =
    Either.cond(
      phoneNumber.matches("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"),
      phoneNumber,
      InvalidPhoneNumber
    )

  private def validateZipCode(
      zipCode: String
  ): Either[RegistrationError, String] =
    Either.cond(
      zipCode.matches("^[0-9]{5}(?:-[0-9]{4})?$"),
      zipCode,
      InvalidZipCode
    )

  private def validateAddress(
      address: String
  ): Either[RegistrationError, String] =
    Either.cond(
      !address.isEmpty(),
      address,
      InvalidAddress
    )

  private def validateCity(city: String): Either[RegistrationError, String] =
    Either.cond(!city.isEmpty(), city, InvalidCity)

  def validateState(state: String): Either[RegistrationError, String] =
    Either.cond(states.map(_.abbrev).contains(state), state, InvalidState)

  private def validateCreatedDate(
      createdDate: String
  ): Either[RegistrationError, LocalDateTime] =
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val parsedDate = Try(LocalDateTime.parse(createdDate, formatter))
    Either.cond(
      parsedDate.isSuccess,
      parsedDate.get,
      InvalidCreatedDate
    )

  def validateRegistration(
      username: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int,
      email: String,
      phone: String,
      address: String,
      zipCode: String,
      city: String,
      state: String,
      createdDate: String
  ): Either[RegistrationError, RegisteredUser] =
    for
      validateUserName <- validateUsername(username)
      validatePassword <- validatePassword(password)
      validateFirstName <- validateFirstName(firstName)
      validateLastName <- validateLastName(lastName)
      validateAge <- validateAge(age)
      validateEmail <- validateEmail(email)
      validatePhone <- validatePhone(phone)
      validateAddress <- validateAddress(address)
      validateZipCode <- validateZipCode(zipCode)
      validateCity <- validateCity(city)
      validateState <- validateState(state)
      validateCreationDate <- validateCreatedDate(createdDate)
    yield RegisteredUser(
      validateUserName,
      validatePassword,
      validateFirstName,
      validateLastName,
      validateAge,
      validateEmail,
      validatePhone,
      validateAddress,
      validateZipCode,
      validateCity,
      validateState,
      validateCreationDate
    )

object EitherValidator extends EitherValidator
