import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] validWidths = new int[] {10, 20, 40, 80};
        int[] validHeights = new int[] {10, 20, 40};

        if (args.length < 1) {
            System.err.println("Uso: java GameOfLife w=<int> h=<int> g=<int> s=<int> p=<string>");
            return;
        }

        int width = -1;
        int height = -1;
        int generations = -1;
        int speed = -1;
        String population = "";

        for (String arg : args) {
            String[] parts = arg.split("=");
            if (parts.length != 2) {
                System.err.println("Argumento no válido: " + arg);
                return;
            }

            String key = parts[0];
            String value = parts[1];

            switch (key) {
                case "w":
                    width = parseAndGetInt(value, validWidths);
                    break;
                case "h":
                    height = parseAndGetInt(value, validHeights);
                    break;
                case "g":
                    try {
                        generations = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        generations = -2;
                    }
                    break;
                case "s":
                    try {
                        speed = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        speed = -2;
                    }
                    break;
                case "p":
                    population = value;

                    String populationValue = population.split("=")[0];

                    if (width == -1 || width == -2) {
                        System.err.println("Population is invalid because width is invalid");
                        population = "-2";
                    }

                    if (height == -1 || height == -2) {
                        System.err.println("Population is invalid because height is invalid");
                        population = "-2";
                        break;
                    }

                    if (populationValue.startsWith("#")) {
                        population = "-2";
                        break;
                    }

                    String[] populationLines = populationValue.split("#");
                    int firstRowLength = populationLines[0].length();

                    for (String line : populationLines) {
                        if (line.length() != firstRowLength) {
                            population = "-2";
                            break;
                        }
                    }

                    int populationRows = populationValue.split("#").length;
                    int populationCols = populationValue.split("#")[0].length();

                    if (populationRows != height || populationCols != width) {
                        population = "-2";
                        break;
                    }

                    break;
            }
        }

        System.out.println("width = [" + (width == -1 ? "No presente" : width == -2 ? "Invalid" : width) + "]");
        System.out.println("height = [" + (height == -1 ? "No presente" : height == -2 ? "Invalid" : height) + "]");
        System.out.println("generations = [" + (generations == -1 ? "No presente" : generations == -2 ? "Invalid" : generations) + "]");
        System.out.println("speed = [" + (speed == -1 ? "No presente" : speed == -2 ? "Invalid" : speed) + "]");
        System.out.println("population = [" + (population.equals("-1") ? "No presente" : population.equals("-2") ? "Invalid" : population) + "]");
    }

    private static int parseAndGetInt(String valueToFormat, int[] validSizes) {
        int value;
        try {
            value = Integer.parseInt(valueToFormat);

            int finalValue = value;
            boolean isValid = Arrays.stream(validSizes).anyMatch(validSize -> finalValue == validSize);

            if (!isValid) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            value = -2;
        }
        return value;
    }
}