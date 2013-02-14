package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.ElementIDImpl.collectBlocks;
import static org.mdl4ui.base.model.ElementIDImpl.collectFields;
import static org.mdl4ui.base.model.ElementIDImpl.collectGroups;
import static org.mdl4ui.base.model.ElementIDImpl.containsRec;
import static org.mdl4ui.ui.sample.EFieldSample.BIRTHDATE;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL_ACCEPTED;
import static org.mdl4ui.ui.sample.EFieldSample.FIRST_NAME;
import static org.mdl4ui.ui.sample.EFieldSample.LANGUAGE;
import static org.mdl4ui.ui.sample.EFieldSample.LAST_NAME;
import static org.mdl4ui.ui.sample.EFieldSample.LOGIN;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD_CONFIRMATION;
import static org.mdl4ui.ui.sample.EFieldSample.TIMEZONE;
import static org.mdl4ui.ui.sample.EGroupSample.EMAIL_GROUP;

import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;

public enum EBlockSample implements BlockID {
    INFORMATIONS(FIRST_NAME, //
                    LAST_NAME, //
                    EMAIL, //
                    BIRTHDATE, //
                    LANGUAGE, //
                    TIMEZONE, //

                    EMAIL_ACCEPTED, //
                    EMAIL_GROUP), //

    ACCOUNT(LOGIN, //
                    PASSWORD, //
                    PASSWORD_CONFIRMATION);

    private final ElementID[] childs;

    private EBlockSample(ElementID... blocks) {
        this.childs = blocks;
    }

    @Override
    public EElementType elementType() {
        return EElementType.BLOCK;
    }

    @Override
    public ElementID[] childs() {
        return childs;
    }

    @Override
    public boolean contains(ElementID child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldID> fields() {
        return collectFields(this);
    }

    @Override
    public List<BlockID> blocks() {
        return collectBlocks(this);
    }

    @Override
    public List<GroupID> groups() {
        return collectGroups(this);
    }
}
