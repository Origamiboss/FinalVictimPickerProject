package src.UIElements.Colors;

import java.awt.*;

public class UIColors {

    final static ColorScheme CHARCOAL = new ColorScheme(Color.decode("#171717"), Color.decode("#444444"), Color.decode("#6F6F6F"));
    final static ColorScheme CREAM = new ColorScheme(Color.decode("#FEFCE3"), Color.decode("#DFD9B3"), Color.decode("#BFB582"));
    final static ColorScheme MOSS = new ColorScheme(Color.decode("#55AA6F"), Color.decode("#499460"), Color.decode("#367D50"));
    final static ColorScheme NAVY = new ColorScheme(Color.decode("#10101F"), Color.decode("#252A42"), Color.decode("#393F5C"));
    final static ColorScheme MARIGOLD = new ColorScheme(Color.decode("#EDE682"), Color.decode("#D8CC6E"), Color.decode("#C2B259"));
    final static ColorScheme CRIMSON = new ColorScheme(Color.decode("#ED4434"), Color.decode("#DA2714"), Color.decode("#9C1C0E"));
    final static ColorScheme EGG = new ColorScheme(Color.decode("#F7F7F7"), Color.decode("#DCDBDF"), Color.decode("#BDBCC2"));
    final static ColorScheme TEAMBLUE = new ColorScheme(Color.decode("#0000FF"), Color.decode("#0102A9"), Color.decode("#00003C"));
    final static ColorScheme TEAMRED = new ColorScheme(Color.decode("#FD1313"), Color.decode("#AF0303"), Color.decode("#6D0101"));
    final static ColorScheme TEAMGREEN = new ColorScheme(Color.decode("#00FF00"), Color.decode("#03C102"), Color.decode("#038602"));
    final static ColorScheme TEAMYELLOW = new ColorScheme(Color.decode("#FFFF00"), Color.decode("#E1E004"), Color.decode("#C1C002"));
    final static ColorScheme MANDARIN = new ColorScheme(Color.decode("#EB813B"), Color.decode("#D66F2B"), Color.decode("#B55718"));
    final static ColorScheme GBBackScreen = new ColorScheme(Color.decode("#CADCA0"), Color.decode("#30622F"), Color.decode("#144713"));
    final static ColorScheme GBFrontScreen = new ColorScheme(Color.decode("#0F380E"), Color.decode("#0C290B"), Color.decode("#071707"));

    final public static String[] colors = {"charcoal", "cream", "moss", "navy", "marigold", "crimson", "egg",
            "teamblue", "teamred", "teamgreen","teamyellow", "mandarin", "gbbackscreen", "gbfrontscreen"};

    public static ColorScheme getColorScheme(String name) {
        switch (name.toLowerCase()) {
            case "charcoal":
                return CHARCOAL;
            case "cream":
                return CREAM;
            case "moss":
                return MOSS;
            case "navy":
                return NAVY;
            case "marigold":
                return MARIGOLD;
            case "crimson":
                return CRIMSON;
            case "egg":
                return EGG;
            case "teamblue":
                return TEAMBLUE;
            case "teamred":
                return TEAMRED;
            case "teamgreen":
                return TEAMGREEN;
            case "teamyellow":
                return TEAMYELLOW;
            case "mandarin":
                return MANDARIN;
            case "gbbackscreen":
                return GBBackScreen;
            case "gbfrontscreen":
                return GBFrontScreen;
            default:
                throw new IllegalArgumentException("Unknown color scheme");
        }
    }

}
