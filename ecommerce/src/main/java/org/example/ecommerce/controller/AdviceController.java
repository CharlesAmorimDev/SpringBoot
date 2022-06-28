package org.example.ecommerce.controller;

import org.example.ecommerce.exception.ApplicationErrorsExceptions;
import org.example.ecommerce.exception.BusinessRuleException;
import org.example.ecommerce.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationErrorsExceptions handleBusiness(BusinessRuleException erro) {
        return new ApplicationErrorsExceptions(erro.getMessage());
    }
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationErrorsExceptions handleOrderNotFound(OrderNotFoundException erro) {
        return new ApplicationErrorsExceptions(erro.getMessage());
    }
}
