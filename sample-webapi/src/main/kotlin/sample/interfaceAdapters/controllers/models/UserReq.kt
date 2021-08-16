package sample.interfaceAdapters.controllers.models

import org.hibernate.validator.constraints.Length
import sample.entities.models.NewUserEntity

class UserReq(
    @get:Length(min = 1, max = 10)
    val account: String
) {
    fun toNewUserEntity(): NewUserEntity =
        NewUserEntity(
            account = account
        )
}
