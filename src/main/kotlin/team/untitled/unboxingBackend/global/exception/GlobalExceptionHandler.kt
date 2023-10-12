package team.untitled.unboxingBackend.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.BindException
import javax.validation.ConstraintViolationException


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UntitledException::class)
    fun handleCustomException(e: UntitledException): ResponseEntity<*> {
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(
                e.status,
                e.message?:"Unknown Error"
            ), HttpStatus.valueOf(e.status)
        )
    }


    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleConstraintViolation(e: ConstraintViolationException): ResponseEntity<*> {
        val errorMap: MutableMap<String, String> = HashMap()
        for (violation in e.getConstraintViolations()) {
            errorMap[violation.propertyPath.toString()] = violation.message
        }
        return ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleServerException(ex: Exception?): ResponseEntity<ExceptionResponse> {
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(
                500, "Internal Server Error"
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}