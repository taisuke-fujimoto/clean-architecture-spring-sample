package sample.entities.models

data class UserEntity(
    val userId: UserId,
    val account: String,
    val name: String
) {
    data class UserId(
        val value: Long
    ) {
        init {
            require(value > 0) { "UserId must be positive. (value: $value)" }
        }
    }

    data class Create(
        val account: String,
        val name: String
    )

    data class Update(
        val userId: UserId,
        val name: String
    )
}
