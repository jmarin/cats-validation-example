package com.jmarin.examples.validation.api

import com.jmarin.examples.validation.model.RegistrationError
import com.jmarin.examples.validation.model.InvalidUserName
import com.jmarin.examples.validation.model.InvalidPassword
import com.jmarin.examples.validation.model.InvalidFirstName
import com.jmarin.examples.validation.model.InvalidLastName
import com.jmarin.examples.validation.model.InvalidAge
import com.jmarin.examples.validation.model.InvalidEmail
import com.jmarin.examples.validation.model.InvalidPhoneNumber
import com.jmarin.examples.validation.model.InvalidZipCode
import com.jmarin.examples.validation.model.InvalidAddress
import com.jmarin.examples.validation.model.InvalidCity
import com.jmarin.examples.validation.model.Geography.*
import com.jmarin.examples.validation.model.InvalidState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try
import com.jmarin.examples.validation.model.InvalidCreatedDate
import cats.data.ValidatedNel
import cats.data.NonEmptyList
import cats.syntax.validated.*
import cats.syntax.apply.*
import com.jmarin.examples.validation.model.RegisteredUser

trait CatsValidation:

  // Type Alias for representing a non emptly list of errors
  // NOTE:In Cats,  type ValidatedNel[+E, +A] = Validated[NonEmptyList[E], A]
  type ErrorOr[A] = ValidatedNel[RegistrationError, A]

  // NOTE: for the syntax below, using invalidNel is a shortcut for
  // NonEmptyList.of("empty first name").invalid[String]

  private def validateUsername(
      userName: String
  ): ErrorOr[String] =
    if (userName.matches("^[a-zA-Z0-9]+$")) then userName.validNel
    else InvalidUserName.invalidNel

  private def validatePassword(
      password: String
  ): ErrorOr[String] =
    if password.matches(
        "(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
      )
    then password.validNel
    else InvalidPassword.invalidNel

  private def validateFirstName(
      firstName: String
  ): ErrorOr[String] =
    if firstName.matches("^[a-zA-Z]+$") then firstName.validNel
    else InvalidFirstName.invalidNel

  private def validateLastName(
      firstName: String
  ): ErrorOr[String] =
    if firstName.matches("^[a-zA-Z]+$") then firstName.validNel
    else InvalidLastName.invalidNel

  private def validateAge(age: Int): ErrorOr[Int] =
    if age >= 18 && age <= 99 then age.validNel
    else InvalidAge.invalidNel

  private def validateEmail(email: String): ErrorOr[String] =
    if email.matches(
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
      )
    then email.validNel
    else InvalidEmail.invalidNel

  private def validatePhone(phoneNumber: String): ErrorOr[String] =
    if phoneNumber.matches("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    then phoneNumber.validNel
    else InvalidPhoneNumber.invalidNel

  private def validateZipCode(zipCode: String): ErrorOr[String] =
    if zipCode.matches("^[0-9]{5}(?:-[0-9]{4})?$") then zipCode.validNel
    else InvalidZipCode.invalidNel

  private def validateAddress(address: String): ErrorOr[String] =
    if !address.isEmpty() then address.validNel
    else InvalidAddress.invalidNel

  private def validateCity(city: String): ErrorOr[String] =
    if !city.isEmpty() then city.validNel
    else InvalidCity.invalidNel

  private def validateState(state: String): ErrorOr[String] =
    if states.map(_.abbrev).contains(state) then state.validNel
    else InvalidState.invalidNel

  private def validateCreatedDate(createdDate: String): ErrorOr[LocalDateTime] =
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val parsedDate = Try(LocalDateTime.parse(createdDate, formatter))
    if parsedDate.isSuccess then parsedDate.get.validNel
    else InvalidCreatedDate.invalidNel

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
  ): ErrorOr[RegisteredUser] =
    (
      validateUsername(username),
      validatePassword(password),
      validateFirstName(firstName),
      validateLastName(lastName),
      validateAge(age),
      validateEmail(email),
      validatePhone(phone),
      validateAddress(address),
      validateZipCode(zipCode),
      validateCity(city),
      validateState(state),
      validateCreatedDate(createdDate)
    ).mapN(RegisteredUser.apply)

object CatsValidation extends CatsValidation
