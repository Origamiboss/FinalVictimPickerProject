package UIElements.Colors;

public class CurrentUITheme {
    private ColorScheme currentBackgroundColor;

    public String getBackgroundString() {
        return backgroundString;
    }

    public void setBackgroundString(String backgroundString) {
        this.backgroundString = backgroundString;
    }

    public String getForegroundString() {
        return foregroundString;
    }

    public void setForegroundString(String foregroundString) {
        this.foregroundString = foregroundString;
    }

    private String backgroundString;
    private String foregroundString;

    public ColorScheme getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }
    public void setCurrentBackgroundColor(String select) {
        this.currentBackgroundColor = UIColors.getColorScheme(select);
        backgroundString = select;
    }


    private ColorScheme currentForegroundColor;
    public ColorScheme getCurrentForegroundColor() {
        return currentForegroundColor;
    }
    public void setCurrentForegroundColor(String select) {
        this.currentForegroundColor = UIColors.getColorScheme(select);
        foregroundString = select;
        //this.currentForegroundColor = currentForegroundColor;
    }

    public CurrentUITheme(){
        currentBackgroundColor = UIColors.getColorScheme("cream");
        currentForegroundColor = UIColors.getColorScheme("charcoal");
        backgroundString = "cream";
        foregroundString = "charcoal";
    }

    public CurrentUITheme(String backSelect, String foreSelect){
        currentBackgroundColor = UIColors.getColorScheme(backSelect);
        currentForegroundColor = UIColors.getColorScheme(foreSelect);
        backgroundString = backSelect;
        foregroundString = foreSelect;
    }

}
