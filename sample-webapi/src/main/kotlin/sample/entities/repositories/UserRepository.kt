package sample.entities.repositories

import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity

interface UserRepository {
    fun getUser(userId: UserEntity.UserId): UserEntity

    fun createUser(data: NewUserEntity): UserEntity
}
