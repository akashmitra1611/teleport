package com.teleport.config;

import com.teleport.dto.CustomMessages;
import com.teleport.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class TrackControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleBadRequest(MethodArgumentNotValidException handledExceptions){
        return ExceptionResponse.builder()
                .code("ERROR:400")
                .message(CustomMessages.INVALID_REQUEST.getValue())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleBadRequest(MethodArgumentTypeMismatchException handledExceptions){
        return ExceptionResponse.builder()
                .code("ERROR:400")
                .message(CustomMessages.INVALID_REQUEST.getValue())
                .build();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleInternalServerError(HttpServerErrorException.InternalServerError handledExceptions){
        return ExceptionResponse.builder()
                .code("ERROR:500")
                .message(handledExceptions.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleInternalServerError(NoSuchAlgorithmException handledExceptions){
        return ExceptionResponse.builder()
                .code("ERROR:500")
                .message(handledExceptions.getMessage())
                .build();
    }

}
