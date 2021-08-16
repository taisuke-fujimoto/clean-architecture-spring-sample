package sample.entities.models

data class UserEntity(
    val userId: UserId,
    val account: String
) {
    data class UserId(
        val value: Long
    )
}
