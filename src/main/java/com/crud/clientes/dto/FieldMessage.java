package com.crud.clientes.dto;

public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage(String message, String fieldName) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
