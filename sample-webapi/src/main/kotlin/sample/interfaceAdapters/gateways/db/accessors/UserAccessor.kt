package sample.interfaceAdapters.gateways.db.accessors

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component
import sample.entities.models.UserEntity
import sample.interfaceAdapters.gateways.db.tables.Users

@Component
class UserAccessor {
    fun findBy(userId: UserEntity.UserId): ResultRow? =
        Users.select { Users.id eq userId.value }.singleOrNull()

    fun insert(entity: UserEntity.Create): InsertStatement<Number> =
        Users.insert {
            it[account] = entity.account
            it[name] = entity.name
        }

    fun update(entity: UserEntity.Update): Int =
        Users.update({ Users.id eq entity.userId.value }) {
            it[name] = entity.name
        }
}
