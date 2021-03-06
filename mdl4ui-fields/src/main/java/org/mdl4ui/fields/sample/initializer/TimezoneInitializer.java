/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.initializer;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.component.ListBoxField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.Timezone;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleInit(@OnField(EFieldSample.TIMEZONE))
public class TimezoneInitializer implements FieldInitializer {

    @Override
    public void init(Field field, FieldEvent event) {
        ListBoxField checkbox = field.getComponent();
        for (Timezone timeZone : Timezone.values()) {
            checkbox.addItem(timeZone.getDisplayName(), timeZone.getCode());
        }
    }
}
