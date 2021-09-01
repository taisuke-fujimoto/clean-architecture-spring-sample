package sample.useCases.models

import sample.entities.models.UserEntity

interface UpdateUserRequest {
    val name: String

    fun toEntity(userId: UserEntity.UserId): UserEntity.Update =
        UserEntity.Update(
            userId = userId,
            name = name
        )
}
