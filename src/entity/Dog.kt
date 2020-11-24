package com.kyoodong.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Dogs : IntIdTable() {
    val owner = varchar("owner", 100)
    val gender = varchar("gender", 1)
    val description = text("description")
    val age = integer("age")
    val breed = integer("breed").references(Breeds.id)
    val profileImage = blob("profile_image").nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
}

class Dog(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Dog>(Dogs)

    val owner by Dogs.owner
    val gender by Dogs.gender
    val description by Dogs.description
    val age by Dogs.age
    val breed by Dogs.breed
    val profileImage by Dogs.profileImage
    val createdAt by Dogs.createdAt
}