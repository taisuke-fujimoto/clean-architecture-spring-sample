package sample.entities.repositories

import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity

interface UserRepository {
    fun get(userId: UserEntity.UserId): UserEntity

    fun create(data: NewUserEntity): UserEntity
}
