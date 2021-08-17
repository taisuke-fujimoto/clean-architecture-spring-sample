package sample.interfaceAdapters.controllers.models

import sample.useCases.models.CreateUserRequest

class CreateUserReq(
    override val account: String,
    override val name: String
) : CreateUserRequest, UserValidation.Create
