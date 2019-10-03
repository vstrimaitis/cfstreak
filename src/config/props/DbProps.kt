package io.cfstreak.config.props

import io.cfstreak.ext.get
import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class DbProps(config: ApplicationConfig) {
    val url = config["db.url"]
    val username = config["db.username"]
    val password = config["db.password"]
    val driver = config["db.driver"]
}
