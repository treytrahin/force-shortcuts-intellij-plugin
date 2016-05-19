package com.treytrahin.compelshortcuts;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.treytrahin.compelshortcuts.dto.ShortcutDTO;

public class PopUpNotifier {

    public static void firePopUp(ShortcutDTO shortcutDTO) {
        Notification tip = new Notification("Compel Shortcuts", "Dude -- there's a shortcut:", buildMessage(shortcutDTO), NotificationType.INFORMATION);
        Notifications.Bus.notify(tip);
    }

    private static String buildMessage(ShortcutDTO shortcutDTO) {
        return shortcutDTO.getShortcutText() + " : " + shortcutDTO.getDescription();
    }
}
