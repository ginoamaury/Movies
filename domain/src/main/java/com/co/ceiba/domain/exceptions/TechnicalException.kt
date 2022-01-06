package com.co.ceiba.domain.exceptions

import java.lang.Exception

private const val TECHNICAL_MESSAGE = "We are having problems, please try later"
class TechnicalException (message: String = TECHNICAL_MESSAGE) : Exception(message)