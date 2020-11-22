package com.kyoodong.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Users : IdTable<String>() {
    override val id: Column<EntityID<String>>
        get() = TODO("Not yet implemented")

    val gender = text("gender")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
}

class User(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, User>(Users)

    var gender by Users.gender
    var createdAt by Users.createdAt
}