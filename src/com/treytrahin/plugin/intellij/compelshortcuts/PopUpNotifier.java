package com.treytrahin.plugin.intellij.compelshortcuts;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class PopUpNotifier {

    public static void firePopUp(ShortcutAction shortcut) {
        Notification tip = new Notification("Compel Shortcuts", "Dude -- there's a shortcut:", buildMessage(shortcut), NotificationType.INFORMATION);
        Notifications.Bus.notify(tip);
    }

    private static String buildMessage(ShortcutAction shortcut) {
        return shortcut.getShortcutText() + " : " + shortcut.getDescription();
    }
}
