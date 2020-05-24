package com.vginert.expensetracker.data.core.exceptions.handlers

import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ExceptionHandlersWrapperTest {

    @Test
    fun `On handle exception should call handle exception in all handlers one time`() {
        val exception: Exception = mockk()
        val handler1: ExceptionHandler = mockk(relaxed = true)
        val handler2: ExceptionHandler = mockk(relaxed = true)
        val handler3: ExceptionHandler = mockk(relaxed = true)
        val exceptionHandlers = listOf(handler1, handler2, handler3)
        val exceptionHandlersWrapper = ExceptionHandlersWrapper(exceptionHandlers)

        exceptionHandlersWrapper.handleException(exception)

        verify(exactly = 1) { handler1.handleException(exception) }
        verify(exactly = 1) { handler2.handleException(exception) }
        verify(exactly = 1) { handler3.handleException(exception) }
    }
}
