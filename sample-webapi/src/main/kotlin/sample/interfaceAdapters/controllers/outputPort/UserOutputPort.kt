package sample.interfaceAdapters.controllers.outputPort

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import sample.entities.models.UserEntity
import sample.interfaceAdapters.controllers.models.UserRes
import sample.useCases.errorCodes.UserErrorCode

interface UserOutputPort {
    fun UserEntity.toUserRes(): UserRes =
        UserRes(
            userId = userId.value,
            account = account
        )

    fun Result<UserEntity, UserErrorCode>.toResponseEntity(): ResponseEntity<*> =
        mapBoth(
            { ResponseEntity(it.toUserRes(), HttpStatus.OK) },
            {
                when (it) {
                    UserErrorCode.NOT_FOUND -> ResponseEntity(HttpStatus.NOT_FOUND)
                    UserErrorCode.DUPLICATE_ACCOUNT -> ResponseEntity(HttpStatus.CONFLICT)
                }
            }
        )
}
