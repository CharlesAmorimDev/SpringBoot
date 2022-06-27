package org.example.ecommerce;

import java.util.Arrays;
import java.util.List;

public class ApplicationErrosExceptions {

    private List<String> errors;

    public ApplicationErrosExceptions(String messageErro){
        errors = Arrays.asList(messageErro);
    }

    public List<String> getErrors() {
        return errors;
    }
}
