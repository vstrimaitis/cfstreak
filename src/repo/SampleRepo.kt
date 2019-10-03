package io.cfstreak.repo

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

internal object Sample : Table() {
    val email = varchar("email", 100).primaryKey()
    val firstName = varchar("first_name", 100).nullable()
    val lastName = varchar("last_name", 100).nullable()
    val password = varchar("password", 60)

    fun resolve(row: ResultRow): User {
        return User(
            row[email],
            row[firstName],
            row[lastName],
            row[password]
        )
    }
}

class UserRepo(private val db: Database) : BaseRepo(db, Users) {

    fun getByEmail(email: String): User? {
        return transaction(db) {
            Users
                .select { Users.email eq email }
                .map { Users.resolve(it) }
                .firstOrNull()
        }
    }

    fun add(user: User) {
        transaction(db) {
            Users.insert {
                it[email] = user.email
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[password] = user.password
            }
        }
    }

    fun update(oldEmail: String, newUser: User) {
        transaction(db) {
            Users.update({ Users.email eq oldEmail }) {
                it[email] = newUser.email
                it[firstName] = newUser.firstName
                it[lastName] = newUser.lastName
                it[password] = newUser.password
            }
        }
    }

    fun delete(email: String) {
        transaction(db) {
            Users.deleteWhere { Users.email eq email }
        }
    }
}
