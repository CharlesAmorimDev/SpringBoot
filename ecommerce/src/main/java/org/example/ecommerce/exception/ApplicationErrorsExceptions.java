package org.example.ecommerce.exception;

import java.util.Arrays;
import java.util.List;

public class ApplicationErrorsExceptions {

    private List<String> errors;

    public ApplicationErrorsExceptions(String messageErro){
        errors = Arrays.asList(messageErro);
    }

    public List<String> getErrors() {
        return errors;
    }
}
