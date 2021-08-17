package sample.useCases.models

import sample.entities.models.UserEntity

interface CreateUserRequest {
    val account: String
    val name: String

    fun toEntity(): UserEntity.Create =
        UserEntity.Create(
            account = account,
            name = name
        )
}
