package io.cfstreak.ext

import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI


@KtorExperimentalAPI
operator fun ApplicationConfig.get(key: String): String = property(key).getString()

@KtorExperimentalAPI
fun ApplicationConfig.getList(key: String): List<String> = property(key).getList()