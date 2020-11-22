package com.kyoodong.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Users : IdTable<String>() {
    val userId = varchar("id", 100).uniqueIndex()
    val email = varchar("email", 100).uniqueIndex()
    val gender = varchar("gender", 1).nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())

    override val id: Column<EntityID<String>> = userId.entityId()
}

class User(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, User>(Users)

    var email by Users.email
    var gender by Users.gender
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
}