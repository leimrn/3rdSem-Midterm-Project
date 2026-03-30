public class TUITheme {
    // Text Formatting
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";

    // Custom Palette
    public static final String ORANGE = "\033[38;5;208m";
    public static final String LIGHT_ORANGE = "\033[38;5;215m";
    public static final String MUTED_TEXT = "\033[38;5;245m";

    // Box Drawing Characters (Smooth corners)
    public static final String TOP_LEFT = "╭";
    public static final String TOP_RIGHT = "╮";
    public static final String BOTTOM_LEFT = "╰";
    public static final String BOTTOM_RIGHT = "╯";
    public static final String HORIZ = "─";
    public static final String VERT = "│";

    // Clears the terminal screen for a clean refresh
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Helper to draw a horizontal line of a specific width
    public static String line(int width) {
        return HORIZ.repeat(width);
    }
}
