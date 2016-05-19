package com.treytrahin.plugin.intellij.compelshortcuts;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.impl.ActionButton;
import com.intellij.openapi.actionSystem.impl.ActionMenuItem;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.util.text.StringUtil;

import java.awt.*;
import java.util.Optional;

public class ShortcutActionFactory {

    public static Optional<ShortcutAction> buildShortcutIfAvailable(Component eventSource) {
        ShortcutAction shortcut = null;

        if (isActionButton(eventSource)) {
            shortcut = buildShortcut((ActionButton) eventSource);
        } else if (isActionMenuItem(eventSource)) {
            shortcut = buildShortcut((ActionMenuItem) eventSource);
        }

        if (shortcut != null && StringUtil.isEmptyOrSpaces(shortcut.getShortcutText())) {
            return Optional.empty();
        }

        return Optional.ofNullable(shortcut);
    }

    private static boolean isActionButton(Component component) {
        return ActionButton.class.isAssignableFrom(component.getClass());
    }

    private static boolean isActionMenuItem(Component component) {
        return ActionMenuItem.class.isAssignableFrom(component.getClass());
    }

    public static ShortcutAction buildShortcut(ActionButton actionButton) {
        AnAction anAction = actionButton.getAction();
        if (anAction == null) {
            return null;
        }

        String shortcutText = KeymapUtil.getFirstKeyboardShortcutText(anAction);
        String description = anAction.getTemplatePresentation().getText();

        return new ShortcutAction(shortcutText, description);
    }

    public static ShortcutAction buildShortcut(ActionMenuItem actionMenuItem) {
        String shortcutText = actionMenuItem.getFirstShortcutText();
        String description = actionMenuItem.getText();

        return new ShortcutAction(shortcutText, description);
    }


}
