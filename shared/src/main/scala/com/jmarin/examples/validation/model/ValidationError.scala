package com.jmarin.examples.validation

trait RegistrationError:
  def errorMessage: String

case object InvalidUserName extends RegistrationError:
  override def errorMessage: String =
    "Username cannot contain special characters"

case object InvalidPassword extends RegistrationError:
  override def errorMessage: String =
    "Password must be at least 12 characters, " +
      "include an uppercase and a lowercase letter, " +
      "one number and one special character"

case object InvalidFirst extends RegistrationError:
  override def errorMessage: String =
    "First name cannot contain spaces, numbers or special characters"

case object InvalidLast extends RegistrationError:
  override def errorMessage: String =
    "Last name cannot contain spaces, numbers or special characters"

case object InvalidAge extends RegistrationError:
  override def errorMessage: String =
    "You must be between the ages of 18 and 99"

case object InvalidEmail extends RegistrationError:
  override def errorMessage: String = "The email format is invalid"

case object InvalidPhoneNumber extends RegistrationError:
  override def errorMessage: String =
    "The phone number must be in the following format: (XXX)-XXX-XXXX"

case object InvalidAddress extends RegistrationError:
  override def errorMessage: String = "The address is not valid"

case object InvalidZipCode extends RegistrationError:
  override def errorMessage: String = "Zip code format is invalid"

case object InvalidCreatedDate extends RegistrationError:
  override def errorMessage: String =
    "The Created Data is invalid or earlier than year 2000"
