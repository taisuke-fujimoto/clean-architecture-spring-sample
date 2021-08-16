package sample.useCases.exceptions

class DuplicateKeyException(override val cause: Throwable?) : BusinessException()
