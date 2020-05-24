package com.vginert.expensetracker.domain.core.exceptions

interface ExceptionHandler {
    fun handleException(exception: Throwable)
}
