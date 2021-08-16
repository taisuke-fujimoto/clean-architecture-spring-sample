package sample.useCases.exceptions

class DataNotFoundException(override val message: String = "") : BusinessException()
