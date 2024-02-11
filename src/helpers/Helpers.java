package helpers;

import data.ArgumentErrorTypes;

public class Helpers {
    public static String formatArgument(String argument, int value) {
        if (value == ArgumentErrorTypes.NO_PROVIDED) {
            return argument + " = [No present]";
        } else if (value == ArgumentErrorTypes.NO_VALID) {
            return argument + " = [Invalid]";
        } else {
            return argument + " = [" + value + "]";
        }
    }

    public static String formatArgument(String argument, String value) {
        if (value.equals(String.valueOf(ArgumentErrorTypes.NO_PROVIDED))) {
            return argument + " = [No present]";
        } else if (value.equals(String.valueOf(ArgumentErrorTypes.NO_VALID))) {
            return argument + " = [Invalid]";
        } else {
            return argument + " = [\"" + value + "\"]";
        }
    }
}
