package sample.useCases.interactor

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.springframework.stereotype.Service
import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity
import sample.entities.repositories.UserRepository
import sample.useCases.errorCodes.UserErrorCode
import sample.useCases.exceptions.DataNotFoundException
import sample.useCases.exceptions.DuplicateKeyException
import sample.useCases.inputPort.UserUseCase

@Service
class UserInteractor(
    private val userRepository: UserRepository
) : UserUseCase {
    override fun getUser(userId: Long): Result<UserEntity, UserErrorCode> {
        return try {
            Ok(userRepository.getUser(UserEntity.UserId(userId)))
        } catch (ex: DataNotFoundException) {
            Err(UserErrorCode.NOT_FOUND)
        }
    }

    override fun createUser(data: NewUserEntity): Result<UserEntity, UserErrorCode> {
        return try {
            Ok(userRepository.createUser(data))
        } catch (ex: DuplicateKeyException) {
            Err(UserErrorCode.DUPLICATE_ACCOUNT)
        }
    }
}
