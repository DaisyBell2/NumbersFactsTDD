package com.daisybell.numbersfactstdd.numbers.domain

/**
 * @author DaisyBell on 15.06.2023
 */
abstract class DomainException : InstantiationException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()