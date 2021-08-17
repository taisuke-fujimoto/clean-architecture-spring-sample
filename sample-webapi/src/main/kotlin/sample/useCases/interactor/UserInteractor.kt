package sample.useCases.interactor

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sample.entities.exceptions.DataNotFoundException
import sample.entities.exceptions.DuplicateKeyException
import sample.entities.models.UserEntity
import sample.entities.repositories.UserRepository
import sample.useCases.inputPort.UserUseCase
import sample.useCases.models.CreateUserRequest
import sample.useCases.models.UserErrorCode

@Transactional
@Service
class UserInteractor(
    private val userRepository: UserRepository
) : UserUseCase {
    override fun getUser(userId: Long): Result<UserEntity, UserErrorCode> {
        return try {
            Ok(userRepository.get(UserEntity.UserId(userId)))
        } catch (ex: DataNotFoundException) {
            Err(UserErrorCode.NOT_FOUND)
        }
    }

    override fun createUser(data: CreateUserRequest): Result<UserEntity, UserErrorCode> {
        val entity = data.toNewUserEntity()

        return try {
            Ok(userRepository.create(entity))
        } catch (ex: DuplicateKeyException) {
            Err(UserErrorCode.DUPLICATE_ACCOUNT)
        }
    }
}
