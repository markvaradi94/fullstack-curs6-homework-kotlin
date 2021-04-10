package ro.fasttrackit.productsapp.exceptions

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handleProductNotFoundException(exception: ProductNotFoundException): ApiError = ApiError(exception.message)

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleNoSuchElementException(exception: NoSuchElementException): ApiError = ApiError(exception.message)
}

class ProductNotFoundException(override val message: String) : RuntimeException(message)

class NoSuchElementException(override val message: String) : RuntimeException(message)

data class ApiError(val message: String)
