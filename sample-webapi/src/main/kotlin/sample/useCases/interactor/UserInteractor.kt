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
import sample.useCases.models.UpdateUserRequest
import sample.useCases.models.UserErrorCode

@Transactional
@Service
class UserInteractor(
    private val userRepository: UserRepository
) : UserUseCase {
    override fun getUser(userId: Long): Result<UserEntity, UserErrorCode> {
        return try {
            Ok(userRepository.getOne(UserEntity.UserId(userId)))
        } catch (ex: DataNotFoundException) {
            Err(UserErrorCode.NOT_FOUND)
        }
    }

    override fun createUser(data: CreateUserRequest): Result<UserEntity, UserErrorCode> {
        val entity = data.toEntity()

        return try {
            val userId = userRepository.create(entity)
            Ok(userRepository.getOne(userId))
        } catch (ex: DuplicateKeyException) {
            Err(UserErrorCode.DUPLICATE_ACCOUNT)
        }
    }

    override fun updateUser(userId: Long, data: UpdateUserRequest): Result<UserEntity, UserErrorCode> {
        val entity = data.toEntity(UserEntity.UserId(userId))

        return if (userRepository.exists(entity.userId)) {
            userRepository.update(entity)
            Ok((userRepository.getOne(entity.userId)))
        } else {
            Err(UserErrorCode.NOT_FOUND)
        }
    }
}
