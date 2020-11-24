package com.kyoodong.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Breeds : IntIdTable() {
    val name = varchar("name", 45).uniqueIndex()
    val category = varchar("category", 10)
}

class Breed(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Breed>(Breeds)

    val name by Breeds.name
    val category by Breeds.category
}