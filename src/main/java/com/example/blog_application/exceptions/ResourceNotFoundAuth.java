package com.example.blog_application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundAuth extends RuntimeException {


        String resourceName;
        String fieldName;

        String fieldStringValue;

        public ResourceNotFoundAuth(String resourceName, String fieldName, String  fieldStringValue){
            super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldStringValue));
            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldStringValue = fieldStringValue;

        }
}
