package sample.interfaceAdapters.controllers.paths

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.interfaceAdapters.controllers.models.CreateUserReq
import sample.interfaceAdapters.controllers.models.UpdateUserReq
import sample.interfaceAdapters.controllers.outputPort.UserOutputPort
import sample.useCases.inputPort.UserUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(
    private val userUseCase: UserUseCase
) : UserOutputPort {
    @GetMapping("/{userId}")
    fun getByUserId(@PathVariable userId: Long): ResponseEntity<*> =
        userUseCase.getUser(userId).toResponseEntity()

    @PostMapping
    fun post(@RequestBody @Valid data: CreateUserReq): ResponseEntity<*> =
        userUseCase.createUser(data).toResponseEntity()

    @PutMapping("/{userId}")
    fun putByUserId(
        @PathVariable userId: Long,
        @RequestBody @Valid data: UpdateUserReq
    ): ResponseEntity<*> =
        userUseCase.updateUser(userId, data).toResponseEntity()
}
