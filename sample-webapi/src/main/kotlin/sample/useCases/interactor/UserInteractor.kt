package sample.useCases.interactor

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sample.entities.models.NewUserEntity
import sample.entities.models.UserEntity
import sample.entities.repositories.UserRepository
import sample.useCases.errorCodes.UserErrorCode
import sample.entities.exceptions.DataNotFoundException
import sample.entities.exceptions.DuplicateKeyException
import sample.useCases.inputPort.UserUseCase

@Transactional
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
