package sample.interfaceAdapters.controllers.models

import sample.useCases.models.UpdateUserRequest

class UpdateUserReq(
    override val name: String
) : UpdateUserRequest, UserValidation.Update
