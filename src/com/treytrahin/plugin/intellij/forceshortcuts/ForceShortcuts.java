package com.treytrahin.plugin.intellij.forceshortcuts;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.impl.EditorComponentImpl;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class ForceShortcuts implements ApplicationComponent, AWTEventListener {

    public void eventDispatched(AWTEvent event) {
        if (isLeftMouseClick(event)) {
            handleMouseEvent((MouseEvent) event);
        }
    }

    private void handleMouseEvent(MouseEvent event) {
        final Object source = event.getSource();

        if (isNotComponent(source) || isEditorComponent(source)) {
            return;
        }

        Component sourceComponent = (Component) source;
        Optional<ShortcutAction> shortcutAction = ShortcutActionFactory.buildShortcutIfAvailable(sourceComponent);

        if (shortcutAction.isPresent()) {
            PopUpNotifier.firePopUp(shortcutAction.get());
            renderClickFutile(event);
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

    private boolean isLeftMouseClick(AWTEvent event) {
        return event.getID() == MouseEvent.MOUSE_RELEASED && ((MouseEvent) event).getButton() == MouseEvent.BUTTON1;
    }

    public void initComponent() {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
    }

    public void disposeComponent() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
    }

    @NotNull
    public String getComponentName() {
        return "Force Shortcuts";
    }
}
