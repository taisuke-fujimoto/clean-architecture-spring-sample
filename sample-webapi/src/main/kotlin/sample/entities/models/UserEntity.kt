package sample.entities.models

data class UserEntity(
    val userId: UserId,
    val account: String,
    val name: String
) {
    data class UserId(
        val value: Long
    )

    data class Create(
        val account: String,
        val name: String
    )

    data class Update(
        val userId: UserId,
        val name: String
    )
}
