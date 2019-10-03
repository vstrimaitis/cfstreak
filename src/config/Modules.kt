package io.cfstreak.config

import com.typesafe.config.ConfigFactory
import io.cfstreak.cf.api.CodeforcesApi
import io.cfstreak.config.props.DbProps
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.config.ApplicationConfig
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module

@KtorExperimentalAPI
object Modules {
    private val configModule = module {
        single<ApplicationConfig> { HoconApplicationConfig(ConfigFactory.load()) }
    }

    private val cfModule = module {
        single<CodeforcesApi> { CodeforcesApi(get()) }
    }

    private val httpModule = module {
        single<HttpClient> {
            HttpClient {
                install(JsonFeature) {
                    serializer = JacksonSerializer()
                }
            }
    }
    }

    internal val allModules = listOf(
        configModule,
        httpModule,
        cfModule
    )
}