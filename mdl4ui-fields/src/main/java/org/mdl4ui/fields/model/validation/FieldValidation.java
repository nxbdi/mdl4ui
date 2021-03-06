package org.mdl4ui.fields.model.validation;

import java.util.Collection;

import org.mdl4ui.base.model.FieldID;

public class FieldValidation {

    public static boolean valid(Collection<FieldValidation> validations) {
        boolean allValid = true;
        for (FieldValidation val : validations) {
            allValid &= val.isValid();
        }
        return allValid;
    }

    private final FieldID fieldId;
    private final String message;
    private final ErrorType errorType;

    public FieldValidation(FieldID fieldId) {
        this(fieldId, null);
    }

    public FieldValidation(FieldID fieldId, String message) {
        this(fieldId, null, ErrorType.EMPTY_FIELD);
    }

    public FieldValidation(FieldID fieldId, String message, ErrorType kind) {
        this.fieldId = fieldId;
        this.message = message;
        this.errorType = kind;
    }

    public FieldID getFieldID() {
        return this.fieldId;
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public boolean isValid() {
        return message == null;
    }
}
