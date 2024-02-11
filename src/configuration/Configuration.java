package configuration;

import data.ArgumentValidations;

public class Configuration {
    public static void withValidWidths(int[] validWidths) {
        ArgumentValidations.getInstance().setValidWidths(validWidths);
    }

    public static void withValidHeights(int[] validHeights) {
        ArgumentValidations.getInstance().setValidHeights(validHeights);
    }
}
