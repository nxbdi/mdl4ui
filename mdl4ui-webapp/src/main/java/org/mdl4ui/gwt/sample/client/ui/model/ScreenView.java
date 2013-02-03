package org.mdl4ui.gwt.sample.client.ui.model;

import static org.mdl4ui.fields.model.event.SimpleEventBus.EVENT_BUS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.event.BlockModifiedEvent;
import org.mdl4ui.fields.model.event.BlockModifiedEvent.BlockModifiedEventHandler;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent.BlockSubmitedHandler;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ScreenView implements IsWidget {

    private final FluidRow row;
    private final Map<BlockID, BlockView> blocksByIds = new HashMap<BlockID, BlockView>();

    public ScreenView(final Screen screen) {
        row = new FluidRow();
        final Column column = new Column(4, 4);
        row.add(column);

        for (Block block : screen.getBlocks()) {
            final BlockView blockView = new BlockView(block);
            column.add(blockView);
            blocksByIds.put(block.getBlockId(), blockView);
            if (screen.getScreenId().blocks().get(0) == block.getBlockId()) {
                blockView.expand();
            } else {
                blockView.collapse();
            }
        }

        // FIXME move handlers to wizard
        EVENT_BUS.subscribe(BlockSubmitedEvent.TYPE, new BlockSubmitedHandler() {
            @Override
            public void onSubmit(BlockSubmitedEvent event) {
                final List<BlockID> blocks = screen.getScreenId().blocks();
                final int index = blocks.indexOf(event.getBlockID());
                if (index < blocks.size() - 1) {
                    expand(screen, blocks.get(index + 1));
                }
            }
        });

        // FIXME move handlers to wizard
        EVENT_BUS.subscribe(BlockModifiedEvent.TYPE, new BlockModifiedEventHandler() {

            @Override
            public void onModify(BlockModifiedEvent event) {
                expand(screen, event.getBlockID());
            }
        });
    }

    private void expand(final Screen screen, BlockID expanded) {
        final List<BlockID> blocks = screen.getScreenId().blocks();
        if (blocks.contains(expanded)) {
            blocksByIds.get(expanded).expand();
            for (BlockID blockID : blocks) {
                if (expanded != blockID) {
                    blocksByIds.get(blockID).collapse();
                }
            }
        }
    }

    @Override
    public Widget asWidget() {
        return row;
    }
}
