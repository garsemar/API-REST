package com.garsemar.routes;

import com.garsemar.plugins.configureRouting
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomerRoutesKtTest {

    @Test
    fun testCustomer() = testApplication {
        application {
            configureRouting()
        }
        client.get("/customer").apply {

        }
    }
}