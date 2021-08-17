package sample.useCases.models

import sample.entities.models.NewUserEntity

interface CreateUserRequest {
    val account: String
    val name: String

    fun toNewUserEntity(): NewUserEntity =
        NewUserEntity(
            account = account,
            name = name
        )
}
