package com.treytrahin.plugin.intellij.forceshortcuts.config;

import java.util.concurrent.atomic.AtomicBoolean;

public class ForceShortcutsConfig {
    private static ForceShortcutsConfig instance = new ForceShortcutsConfig();
    private AtomicBoolean forceShortcuts = new AtomicBoolean(true);

    public boolean getForceShortcuts() {
        return forceShortcuts.get();
    }

    public void setForceShortcuts(boolean value) {
        forceShortcuts.set(value);
    }

    public static ForceShortcutsConfig getInstance() {
        return instance;
    }
}
