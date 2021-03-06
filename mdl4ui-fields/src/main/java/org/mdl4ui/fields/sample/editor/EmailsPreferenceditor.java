package org.mdl4ui.fields.sample.editor;

import static com.google.common.collect.Collections2.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.CheckBoxGroupField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.EmailType;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

import com.google.common.base.Function;

@InjectSampleEditor(@OnField(EFieldSample.EMAILS_PREFERENCES))
public class EmailsPreferenceditor extends SampleEditor {
    private final ValidationMessages messages;

    public EmailsPreferenceditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        Collection<EmailType> emailTypes = context.getUserAccount().getEmailTypes();
        return emailTypes != null ? emailTypes.toString() : null;
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        Collection<EmailType> emailTypes = context.getUserAccount().getEmailTypes();
        if (emailTypes != null) {
            CheckBoxGroupField checkbox = field.getComponent();
            List<String> value = new ArrayList<String>(transform(emailTypes, new Function<EmailType, String>() {
                @Override
                public String apply(EmailType input) {
                    return input.name();
                }
            }));
            checkbox.setValue(value);
        }
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        CheckBoxGroupField checkbox = field.getComponent();
        List<EmailType> value = new ArrayList<EmailType>(transform(checkbox.getValue(),
                        new Function<String, EmailType>() {
                            @Override
                            public EmailType apply(String input) {
                                return EmailType.valueOf(input);
                            }
                        }));
        context.getUserAccount().setEmailTypes(value);
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        CheckBoxGroupField checkbox = field.getComponent();
        if (checkbox.isEmpty()) {
            return error(field, messages.please_specify_the_kind_of_email_you_wish_to_receive());
        }
        return valid(field);
    }
}
