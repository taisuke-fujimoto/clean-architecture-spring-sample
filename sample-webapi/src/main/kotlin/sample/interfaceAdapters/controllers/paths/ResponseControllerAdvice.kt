package sample.interfaceAdapters.controllers.paths

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice(basePackageClasses = [ResponseControllerAdvice::class])
class ResponseControllerAdvice {
    private val logger: Log = LogFactory.getLog(javaClass)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException) {
        logger.debug("[handleConstraintViolationException]", ex)
    }
}
