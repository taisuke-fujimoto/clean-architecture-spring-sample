package sample.interfaceAdapters.controllers.models

import org.hibernate.validator.constraints.Length

interface UserValidation {
    interface Common {
        @get:Length(min = 1, max = 20)
        val name: String
    }

    interface Create : Common {
        @get:Length(min = 1, max = 10)
        val account: String
    }
}
