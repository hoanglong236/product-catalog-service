package com.lhstore.productcatalogservice.exception;

import com.lhstore.productcatalogservice.common.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ResourceAlreadyExistsException.class })
    public ErrorResponseBody handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        final ErrorResponseBody errorResponseBody = new ErrorResponseBody(HttpStatus.BAD_REQUEST);
        errorResponseBody.addMessage(e.getErrorMessage());
        return errorResponseBody;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ ResourceNotFoundException.class })
    public ErrorResponseBody handleResourceNotFoundException(ResourceNotFoundException e) {
        final ErrorResponseBody errorResponseBody = new ErrorResponseBody(HttpStatus.NOT_FOUND);
        errorResponseBody.addMessage(e.getErrorMessage());
        return errorResponseBody;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ErrorResponseBody handleValidationExceptions(MethodArgumentNotValidException e) {
        final ErrorResponseBody errorResponseBody = new ErrorResponseBody(HttpStatus.BAD_REQUEST);
        final List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        for (final ObjectError error : allErrors) {
            final String errorMessage = error.getDefaultMessage();
            errorResponseBody.addMessage(errorMessage);
        }

        return errorResponseBody;
    }
}
