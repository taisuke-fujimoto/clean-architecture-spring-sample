package sample.useCases.inputPort

import com.github.michaelbull.result.Result
import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity
import sample.useCases.errorCodes.UserErrorCode

interface UserUseCase {
    fun getUser(userId: Long): Result<UserEntity, UserErrorCode>

    fun createUser(data: NewUserEntity): Result<UserEntity, UserErrorCode>
}
