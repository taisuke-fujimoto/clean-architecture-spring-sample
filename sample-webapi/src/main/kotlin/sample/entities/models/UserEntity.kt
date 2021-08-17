package sample.entities.models

data class UserEntity(
    val userId: UserId,
    val account: String,
    val name: String
) {
    data class UserId(
        val value: Long
    )
}
