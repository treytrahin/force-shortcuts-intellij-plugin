# Force Shortcuts

Forces the user to use keyboard shortcuts by blocking mouse clicks on buttons that have keybindings, and displaying a popup of what the keybinding is.

Inspired by the [Key Promoter](https://plugins.jetbrains.com/plugin/1003) plugin by Dmitry Kashin.

# Build Instructions

To work on this project:
 - Import the source into IntelliJ
 - Create a run configuration of type `plugin` (use IntelliJ's JRE)
 - To build a loadable plugin, from the `Build` menu bar drop down select `Prepare Plugin Module 'force-shortcuts' For Deployment` 
    * This will build a .jar file in the root directory of the project. You can choose `Install plugin from disk` under `Plugins` in the settings window

## Known Issues

* Top menu bar actions are not blocked
    * Haven't figured out how to catch those yet.
* Right click menu stays open after blocked click
    * The menu closes when the shortcut is used, but it feels clunky