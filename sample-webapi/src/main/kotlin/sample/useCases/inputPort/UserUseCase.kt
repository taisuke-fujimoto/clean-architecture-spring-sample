package sample.useCases.inputPort

import com.github.michaelbull.result.Result
import sample.entities.models.UserEntity
import sample.useCases.models.CreateUserRequest
import sample.useCases.models.UpdateUserRequest
import sample.useCases.models.UserErrorCode

interface UserUseCase {
    fun getUser(userId: Long): Result<UserEntity, UserErrorCode>

    fun createUser(data: CreateUserRequest): Result<UserEntity, UserErrorCode>

    fun updateUser(userId: Long, data: UpdateUserRequest): Result<UserEntity, UserErrorCode>
}
