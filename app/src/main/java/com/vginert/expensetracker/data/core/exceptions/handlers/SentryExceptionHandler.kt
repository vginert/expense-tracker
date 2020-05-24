package com.vginert.expensetracker.data.core.exceptions.handlers

import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler

class SentryExceptionHandler: ExceptionHandler {

    // TODO implement
    override fun handleException(exception: Throwable) {
        // Not implemented!!! Only to show that we can send te exception multiple platforms
        // like Sentry to track it
    }
}
