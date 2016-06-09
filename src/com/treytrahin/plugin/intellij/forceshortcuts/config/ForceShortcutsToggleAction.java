package com.treytrahin.plugin.intellij.forceshortcuts.config;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;

public class ForceShortcutsToggleAction extends ToggleAction {
    private ForceShortcutsConfig config = ForceShortcutsConfig.getInstance();

    @Override
    public boolean isSelected(AnActionEvent anActionEvent) {
        return config.getForceShortcuts();
    }

    @Override
    public void setSelected(AnActionEvent anActionEvent, boolean isSelected) {
        config.setForceShortcuts(isSelected);
    }
}
