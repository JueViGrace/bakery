package com.bakery.web.users.data.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object UserTable : IntIdTable("users") {
    val name = varchar("name", 50)
    val lastname = varchar("lastname", 50)
    val email = text("email").uniqueIndex("user_email")
    val birthDate = timestamp("birth_date")
    val phone = varchar("phone", 25)
    val createdAt = timestamp("created_at")
}

class UserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDao>(UserTable)

    var name by UserTable.name
    var lastname by UserTable.lastname
    var email by UserTable.email
    var birthDate by UserTable.birthDate
    var phone by UserTable.phone
    var createdAt by UserTable.createdAt
}
