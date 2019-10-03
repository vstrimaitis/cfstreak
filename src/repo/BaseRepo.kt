package io.cfstreak.repo

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BaseRepo(db: Database, private vararg val tables: Table) {
    init {
        transaction(db) {
            for(table in tables) {
                SchemaUtils.create(table)
            }
        }
    }
}