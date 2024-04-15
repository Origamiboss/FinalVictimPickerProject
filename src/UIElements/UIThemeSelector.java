package src.UIElements;

import src.UIElements.Colors.CurrentUITheme;

public class UIThemeSelector {

    private final CurrentUITheme currentUITheme;
    private final UISelection uiSelection;

    public UIThemeSelector(CurrentUITheme currentUITheme, UISelection uiSelection) {
        this.currentUITheme = currentUITheme;
        this.uiSelection = uiSelection;
    }

    public void updateTheme() {
        String selectedTheme = uiSelection.selectTheme();
        String[] selections = selectedTheme.split(",");
        for (String selection : selections) {
            String[] parts = selection.split(":");
            if (parts.length == 2) {
                switch (parts[0]) {
                    case "background":
                        currentUITheme.setCurrentBackgroundColor(parts[1]);
                        break;
                    case "foreground":
                        currentUITheme.setCurrentForegroundColor(parts[1]);
                        break;
                    default:
                        System.out.println("Unknown theme part: " + parts[0]);
                        break;
                }
            }
        }
    }
}
