package com.vginert.expensetracker.data.core.exceptions.handlers

import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler

class ExceptionHandlersWrapper(
    private val exceptionHandlers: List<ExceptionHandler>
) : ExceptionHandler {
    
    override fun handleException(exception: Throwable) =
        exceptionHandlers.forEach { it.handleException(exception) }
}
