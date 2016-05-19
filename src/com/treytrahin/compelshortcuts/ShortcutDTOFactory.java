package com.treytrahin.compelshortcuts;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.impl.ActionButton;
import com.intellij.openapi.actionSystem.impl.ActionMenuItem;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.treytrahin.compelshortcuts.dto.ShortcutDTO;

import java.awt.*;
import java.util.Optional;

public class ShortcutDTOFactory {

    public static Optional<ShortcutDTO> buildShortCutIfAvailable(Component eventSource) {
        ShortcutDTO shortcutDTO = null;

        if (isActionButton(eventSource)) {
            shortcutDTO = buildForceCutFromActionButton((ActionButton) eventSource);
        } else if (isActionMenuItem(eventSource)) {
            shortcutDTO = buildForceCutFromActionMenuItem((ActionMenuItem) eventSource);
        }

        if (shortcutDTO != null && StringUtil.isEmptyOrSpaces(shortcutDTO.getShortcutText())) {
            return Optional.empty();
        }

        return Optional.ofNullable(shortcutDTO);
    }

    private static boolean isActionButton(Component component) {
        return ActionButton.class.isAssignableFrom(component.getClass());
    }

    private static boolean isActionMenuItem(Component component) {
        return ActionMenuItem.class.isAssignableFrom(component.getClass());
    }

    public static ShortcutDTO buildForceCutFromActionButton(ActionButton actionButton) {
        AnAction anAction = actionButton.getAction();
        if (anAction == null) {
            return null;
        }

        String shortcutText = KeymapUtil.getFirstKeyboardShortcutText(anAction);
        String description = anAction.getTemplatePresentation().getText();

        return new ShortcutDTO(shortcutText, description);
    }

    public static ShortcutDTO buildForceCutFromActionMenuItem(ActionMenuItem actionMenuItem) {
        String shortcutText = actionMenuItem.getFirstShortcutText();
        String description = actionMenuItem.getText();

        return new ShortcutDTO(shortcutText, description);
    }


}
