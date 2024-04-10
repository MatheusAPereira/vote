package br.com.voting.vote.handler;


import br.com.voting.vote.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(NotFoundException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .title(NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(NotValidException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionDetails handlerNotValidException(NotValidException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(UNPROCESSABLE_ENTITY.value())
                .title(UNPROCESSABLE_ENTITY.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(HasAlreadyVotedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionDetails handlerHasAlreadyVotedException(HasAlreadyVotedException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(UNAUTHORIZED.value())
                .title(UNAUTHORIZED.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(ExpiredException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionDetails handlerExpiredException(ExpiredException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(UNAUTHORIZED.value())
                .title(UNAUTHORIZED.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}
