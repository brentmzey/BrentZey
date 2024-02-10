package com.brentzey

import Greeting
import SERVER_PORT
import TenantConstants.findContextKeyOpt
import TenantConstants.findTenantId
//import arrow.core.None
//import arrow.core.Some
//import arrow.core.toOption
import com.benasher44.uuid.uuid4
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.User
import models.id.UserId

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Ktor: ${greeting().greet()}")
        }
    }
}

private fun greeting(): Greeting {
    val userUuid = uuid4()
    println("UserId: $userUuid")
    val personName = "Brent"
    val tenantContextKeyOpt = findContextKeyOpt(personName)
    val tenantId = findTenantId(tenantContextKeyOpt)
//    val tenantId = when (tenantIdOpt) {
//        is Some -> tenantIdOpt.value
//        None -> throw IllegalArgumentException("No valid tenantId exists for runtime" +
//            " context $personName")
//    }
    val userInitState = User(
        UserId(userUuid), tenantId,
        personName, email = null, username = null)
    return Greeting(userInitState)
}
