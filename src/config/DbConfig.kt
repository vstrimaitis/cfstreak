package io.cfstreak.config

import io.cfstreak.config.props.DbProps
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database

@KtorExperimentalAPI
class DbConfig(dbProps: DbProps) {
    val database = Database.connect(
        url = dbProps.url,
        user = dbProps.username,
        password = dbProps.password,
        driver = dbProps.driver
    )
}