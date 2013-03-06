package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.PasswordField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.PASSWORD))
public class PasswordEditor extends DefaultEditor {
    private ValidationMessages messages;

    public PasswordEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        return sampleContext.getUserAccount().getPassword();
    }

    @Override
    public void updateFromContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        PasswordField textbox = field.getComponent();
        textbox.setValue(sampleContext.getUserAccount().getPassword());
    }

    @Override
    public void updateContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        PasswordField textbox = field.getComponent();
        SampleContext sampleContext = (SampleContext) context;
        sampleContext.getUserAccount().setPassword(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context, FieldEvent fieldEvent) {
        PasswordField textbox = field.getComponent();
        String value = textbox.getValue();
        if (value == null || value.trim().length() == 0) {
            return error(field, messages.please_specify_your_password());
        }
        return valid(field);
    }
}
