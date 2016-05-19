package com.treytrahin.compelshortcuts.dto;

public class ShortcutDTO {
    private String shortcutText = "";
    private String description = "";

    public ShortcutDTO(String shortcutText, String description) {
        this.shortcutText = shortcutText;
        this.description = description;
    }

    public String getShortcutText() {
        return shortcutText;
    }

    public String getDescription() {
        return description;
    }
}
