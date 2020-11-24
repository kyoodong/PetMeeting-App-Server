package com.kyoodong.config

import com.kyoodong.entity.Breeds
import com.kyoodong.entity.Dogs
import com.kyoodong.entity.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create

object DatabaseInitializer {
    fun init() {
        Database.connect(HikariDataSource(hikariConfig()))
        transaction {
            create(Users)
            create(Breeds)
            create(Dogs)
        }
    }
}

private fun hikariConfig() = HikariConfig().apply {
    driverClassName = "org.h2.Driver"
    jdbcUrl = "jdbc:h2:file:./h2db"
    maximumPoolSize = 3
    isAutoCommit = false
    username = "sa"
    password = "sa"
    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    validate()
}

suspend fun <T> query(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
        block()
    }
}