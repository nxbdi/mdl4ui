package org.mdl4ui.gwt.model.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.WizardContext;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Container;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.user.client.ui.Widget;

public class DefaultScreenView implements ScreenView {

    private final Container container = new Container();
    private final List<ElementView> childs = new ArrayList<ElementView>();
    private final Screen screen;

    public DefaultScreenView(final Screen screen) {
        this.screen = screen;
        for (Block block : screen.blocks()) {
            Row row = new Row();
            container.add(row);

            Column column = new Column(10, 1);
            row.add(column);

            BlockView blockView = new BlockView(block);
            column.add(blockView);
            childs.add(blockView);
        }
    }

    @Override
    public final void onDisplay(WizardContext context) {
        // nothing to do
    }

    @Override
    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    @Override
    public ElementType elementType() {
        return ElementType.SCREEN;
    }

    @Override
    public List<ElementView> childs() {
        return childs;
    }

    @Override
    public boolean contains(ElementView child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldView> fields() {
        return UIElementImpl.<FieldView, ElementView> collectFields(this);
    }

    @Override
    public List<BlockView> blocks() {
        return UIElementImpl.<BlockView, ElementView> collectBlocks(this);
    }

    @Override
    public List<GroupView> groups() {
        return UIElementImpl.<GroupView, ElementView> collectGroups(this);
    }
}
