package com.treytrahin.compelshortcuts;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.impl.EditorComponentImpl;
import com.treytrahin.compelshortcuts.dto.ShortcutDTO;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class CompelShortcuts implements ApplicationComponent, AWTEventListener {

    public void initComponent() {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
    }

    public void disposeComponent() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
    }

    @NotNull
    public String getComponentName() {
        return "Compel Shortcuts";
    }

    public void eventDispatched(AWTEvent event) {
        if (eventIsLeftMouseClick(event)) {
            handleMouseEvent((MouseEvent) event);
        }
    }

    private boolean eventIsLeftMouseClick(AWTEvent event) {
        return event.getID() == MouseEvent.MOUSE_RELEASED && ((MouseEvent) event).getButton() == MouseEvent.BUTTON1;
    }

    private void handleMouseEvent(MouseEvent e) {
        final Object source = e.getSource();

        if (isNotComponent(source) || isEditorComponent(source)) {
            return;
        }

        Component sourceComponent = (Component) source;
        Optional<ShortcutDTO> forceCut = ShortcutDTOFactory.buildShortCutIfAvailable(sourceComponent);

        if (forceCut.isPresent()) {
            PopUpNotifier.firePopUp(forceCut.get());
            renderClickFutile(e);
        }
    }

    private boolean isNotComponent(Object source) {
        return !Component.class.isAssignableFrom(source.getClass());
    }

    private boolean isEditorComponent(Object source) {
        return source.getClass() == EditorComponentImpl.class;
    }

    private void renderClickFutile(MouseEvent event) {
        //TODO: On context menu clicks the menu stays up awkwardly after this. Make it not do that.
        event.consume();
    }

}
