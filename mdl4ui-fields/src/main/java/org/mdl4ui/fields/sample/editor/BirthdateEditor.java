package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.BIRTHDATE))
public class BirthdateEditor extends DefaultEditor {

    private ValidationMessages messages;

    public BirthdateEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public void updateFromContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        DateField dateField = field.getComponent();
        dateField.setValue(sampleContext.getPerson().getBirthDate());
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
        DateField dateField = field.getComponent();
        SampleContext sampleContext = (SampleContext) context;
        sampleContext.getPerson().setBirthDate(dateField.getValue());
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        DateField dateField = field.getComponent();
        if (dateField.getValue() == null) {
            error(field, messages.please_specify_your_birth_date());
        }
        return valid(field);
    }
}