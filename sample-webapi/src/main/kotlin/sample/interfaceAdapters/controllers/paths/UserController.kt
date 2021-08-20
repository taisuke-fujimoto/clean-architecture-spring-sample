package sample.interfaceAdapters.controllers.paths

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.entities.models.UserEntity
import sample.interfaceAdapters.controllers.models.CreateUserReq
import sample.interfaceAdapters.controllers.models.UpdateUserReq
import sample.interfaceAdapters.controllers.models.UserRes
import sample.useCases.inputPort.UserUseCase
import sample.useCases.models.UserErrorCode
import javax.validation.Valid
import javax.validation.constraints.Min

@Validated
@RestController
@RequestMapping("/user")
class UserController(
    private val userUseCase: UserUseCase
) {
    @GetMapping("/{userId}")
    fun getByUserId(@PathVariable @Min(1) userId: Long): ResponseEntity<*> =
        userUseCase.getUser(userId).toResponseEntity(HttpStatus.OK)

    @PostMapping
    fun post(@RequestBody @Valid data: CreateUserReq): ResponseEntity<*> =
        userUseCase.createUser(data).toResponseEntity(HttpStatus.CREATED)

    @PutMapping("/{userId}")
    fun putByUserId(
        @PathVariable @Min(1) userId: Long,
        @RequestBody @Valid data: UpdateUserReq
    ): ResponseEntity<*> =
        userUseCase.updateUser(userId, data).toResponseEntity(HttpStatus.CREATED)

    // 引数付きの拡張関数は、ここに置かないと実行時エラーになる
    // https://hibernate.atlassian.net/browse/HV-1796
    // https://youtrack.jetbrains.com/issue/KT-40857
    companion object {
        private fun UserEntity.toUserRes(): UserRes =
            UserRes(
                userId = userId.value,
                account = account,
                name = name
            )

        fun Result<UserEntity, UserErrorCode>.toResponseEntity(successStatus: HttpStatus): ResponseEntity<*> =
            mapBoth(
                { ResponseEntity(it.toUserRes(), successStatus) },
                {
                    when (it) {
                        UserErrorCode.NOT_FOUND -> ResponseEntity(HttpStatus.NOT_FOUND)
                        UserErrorCode.DUPLICATE_ACCOUNT -> ResponseEntity(HttpStatus.CONFLICT)
                    }
                }
            )
    }
}
