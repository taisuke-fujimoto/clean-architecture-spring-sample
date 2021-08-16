package sample.interfaceAdapters.gateways.db.accessors

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.springframework.stereotype.Component
import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity
import sample.interfaceAdapters.gateways.db.tables.Users

@Component
class UserAccessor {
    fun findBy(userId: UserEntity.UserId): ResultRow? =
        Users.select { Users.id eq userId.value }.singleOrNull()

    fun insert(data: NewUserEntity): InsertStatement<Number> =
        Users.insert {
            it[account] = data.account
        }
}
