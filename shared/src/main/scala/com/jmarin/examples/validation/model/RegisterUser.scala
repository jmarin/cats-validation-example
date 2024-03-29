package com.jmarin.examples.validation.model

import java.time.LocalDateTime

/** User Validation
  *
  * User needs to be validated against the following conditions
  *
  *   1. username must not contain special characters
  *
  * 2. Password must be at least 12 characters long, include an uppercase and
  * lowercase letter, one number and one special character
  *
  * 3. first must not contain spaces, numbers or special characters
  *
  * 4. last must not contain spaces, numbers or special characters
  *
  * 5. age must be between 18 and 99
  *
  * 6. email should be valid
  *
  * 7. phone should be a valid US phone number
  *
  * 8. address should not be empty
  *
  * 9. zip code should be short or long form (5 digit or 5 digit + "-" 4 digits)
  *
  * 10. created data should be a valid date
  */

final case class RegisteredUser(
    username: String,
    password: String,
    first: String,
    last: String,
    age: Int,
    email: String,
    phone: String,
    address: String,
    zip: String,
    city: String,
    state: String,
    createdDate: LocalDateTime
)
