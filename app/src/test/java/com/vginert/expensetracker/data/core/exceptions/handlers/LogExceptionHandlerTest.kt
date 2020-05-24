package com.vginert.expensetracker.data.core.exceptions.handlers

import android.util.Log
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.Test

class LogExceptionHandlerTest {

    @Test
    fun `On handle exception should `() {
        val tag = "Test exception log handler tag"
        val exception: Exception = mockk(relaxed = true)
        val logExceptionHandler = LogExceptionHandler(tag)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0

        logExceptionHandler.handleException(exception)

        verify { Log.e(tag, exception.message, exception) }

        unmockkStatic(Log::class)
    }
}
