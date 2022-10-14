package com.jmarin.examples.validation

trait RegistrationError:
  def errorMessage: String

case object InvalidUserName extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidPassword extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidFirst extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidLast extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidAge extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidEmail extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidPhoneNumber extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidAddress extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidZipCode extends RegistrationError:
  override def errorMessage: String = ???

case object InvalidCreatedDate extends RegistrationError:
  override def errorMessage: String = ???
