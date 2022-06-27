package org.example.ecommerce.controller;

import org.example.ecommerce.exception.ApplicationErrosExceptions;
import org.example.ecommerce.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationErrosExceptions handle(BusinessRuleException erro) {
        return new ApplicationErrosExceptions(erro.getMessage());
    }
}
