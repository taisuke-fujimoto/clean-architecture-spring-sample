package sample.entities.exceptions

class DuplicateKeyException(override val cause: Throwable?) : EntityException()
