package playground.ui;

/**
 * Created by paul on 04/11/17.
 */
public class Color {
    final static String RESET = "\u001b[0m";

    public final static String BLACK = "\u001b[30m";
    public final static String BLUE = "\u001b[34m";
    public final static String CYAN = "\u001b[36m";
    public final static String GREEN = "\u001b[32m";
    public final static String MAGENTA = "\u001b[35m";
    public final static String RED = "\u001b[31m";
    public final static String WHITE = "\u001b[37m";
    public final static String YELLOW = "\u001b[33m";

    public final static String BG_BLACK = "\u001b[40m";
    public final static String BG_RED = "\u001b[41m";
    public final static String BG_GREEN = "\u001b[42m";
    public final static String BG_YELLOW = "\u001b[43m";
    public final static String BG_BLUE = "\u001b[44m";
    public final static String BG_MAGENTA = "\u001b[45m";
    public final static String BG_CYAN = "\u001b[46m";
    public final static String BG_WHITE = "\u001b[47m";

    // Color types
    public static String black(String text) {
        return BLACK + text + RESET;
    }

    public static String blue(String text) {
        return BLUE + text + RESET;
    }

    public static String cyan(String text) {
        return CYAN + text + RESET;
    }

    public static String green(String text) {
        return GREEN + text + RESET;
    }

    public static String magenta(String text) {
        return MAGENTA + text + RESET;
    }

    public static String red(String text) {
        return RED + text + RESET;
    }

    public static String white(String text) {
        return WHITE + text + RESET;
    }

    public static String yellow(String text) {
        return YELLOW + text + RESET;
    }

    // Background color types
    public static String bgBlack(String text) {
        return BG_BLACK + text + RESET;
    }

    public static String bgBlue(String text) {
        return BG_BLUE + text + RESET;
    }

    public static String bgCyan(String text) {
        return BG_CYAN + text + RESET;
    }

    public static String bgGreen(String text) {
        return BG_GREEN + text + RESET;
    }

    public static String bgMagenta(String text) {
        return BG_MAGENTA + text + RESET;
    }

    public static String bgRed(String text) {
        return BG_RED + text + RESET;
    }

    public static String bgWhite(String text) {
        return BG_WHITE + text + RESET;
    }

    public static String bgYellow(String text) {
        return BG_YELLOW + text + RESET;
    }
}
