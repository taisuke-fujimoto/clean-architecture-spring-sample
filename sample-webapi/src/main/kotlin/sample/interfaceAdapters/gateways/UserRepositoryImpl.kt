package sample.interfaceAdapters.gateways

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.springframework.stereotype.Repository
import sample.entities.exceptions.DataNotFoundException
import sample.entities.exceptions.DuplicateKeyException
import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity
import sample.entities.repositories.UserRepository
import sample.interfaceAdapters.gateways.db.accessors.UserAccessor
import sample.interfaceAdapters.gateways.db.tables.Users

@Repository
class UserRepositoryImpl(
    private val userAccessor: UserAccessor
) : UserRepository {
    private val logger: Log = LogFactory.getLog(javaClass)

    override fun getUser(userId: UserEntity.UserId): UserEntity {
        val data = userAccessor.findBy(userId) ?: throw DataNotFoundException()
        return UserEntity(
            userId = UserEntity.UserId(data[Users.id]),
            account = data[Users.account]
        )
    }

    override fun createUser(data: NewUserEntity): UserEntity {
        val result = try {
            userAccessor.insert(data)
        } catch (ex: ExposedSQLException) {
            logger.error("[createUser] sqlState: ${ex.sqlState}")
            if (ex.sqlState == "23505") {
                throw DuplicateKeyException(ex.cause)
            } else {
                throw ex
            }
        }
        check(result.insertedCount == 1) { "INSERT 件数がおかしい (${result.insertedCount} 件)" }
        val newId = UserEntity.UserId(result[Users.id])

        return getUser(newId)
    }
}
