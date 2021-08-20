package sample.entities.repositories

import sample.entities.models.UserEntity

interface UserRepository {
    fun getOne(userId: UserEntity.UserId): UserEntity

    fun exists(userId: UserEntity.UserId): Boolean

    fun create(entity: UserEntity.Create): UserEntity.UserId

    fun update(entity: UserEntity.Update)
}
