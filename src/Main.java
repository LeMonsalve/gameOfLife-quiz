public class Main {

  private static final int NO_PROVIDED = -1;
  private static final int INVALID = -2;

  private static final int[] validWidths = new int[]{10, 20, 40, 80};
  private static final int[] validHeights = new int[]{10, 20, 40};

  private static int width = NO_PROVIDED;
  private static int height = NO_PROVIDED;
  private static int generations = NO_PROVIDED;
  private static int speed = NO_PROVIDED;
  private static String population = String.valueOf(NO_PROVIDED);
  private static String[] arguments;

  public static void main(String[] args) {
    arguments = args;

    parse();

    if (args.length < 1) {
      logRequirements();
    }

    logArguments();
  }

  public static void parse() {
    width = parseWidth();
    height = parseHeight();
    generations = parseGenerations();
    speed = parseSpeed();
    population = parsePopulation();
  }

  private static int parseWidth() {
    int width = parseIntArg("w");

    if (width == NO_PROVIDED) {
      return width;
    }

    boolean isValid = isValidInt(width, validWidths);

    if (!isValid) {
      width = INVALID;
    }

    return width;
  }

  private static int parseHeight() {
    int height = parseIntArg("h");

    if (height == NO_PROVIDED) {
      return height;
    }

    boolean isValid = isValidInt(height, validHeights);

    if (!isValid) {
      height = INVALID;
    }

    return height;
  }

  private static int parseGenerations() {
    int generations = parseIntArg("g");
    return generations;
  }

  private static int parseSpeed() {
    int speed = parseIntArg("s");

    if (speed == NO_PROVIDED) {
      return speed;
    }

    if (speed < 250 || speed > 1000) {
      return INVALID;
    }

    return speed;
  }

  private static String parsePopulation() {
    String population = findArgByLetter("p");
    String populationValue;

    try {
      populationValue = population.split("=")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      population = String.valueOf(NO_PROVIDED);
      return population;
    }

    if (width == -1 || width == -2) {
      logError("Population is invalid because width is invalid or not present");
      population = String.valueOf(INVALID);
      return population;
    }

    if (height == -1 || height == -2) {
      logError("Population is invalid because height is invalid or not present");
      population = String.valueOf(INVALID);
      return population;
    }

    if (populationValue.equalsIgnoreCase("rnd") || populationValue.equalsIgnoreCase("random")) {
      population = generateRandomPopulation();
      return population;
    }

    String[] populationLines = populationValue.split("#");
    int firstRowLength = populationLines[0].length();

    for (String line : populationLines) {
      if (line.length() != firstRowLength) {
        population = String.valueOf(INVALID);
        return population;
      }
    }

    int populationRows = populationValue.split("#").length;
    int populationCols = populationValue.split("#")[0].length();

    if (populationRows > height || populationCols > width) {
      population = String.valueOf(INVALID);
      return population;
    }

    population = populationValue;

    return population;
  }

  private static String generateRandomPopulation() {
    int[][] cells = new int[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        cells[i][j] = (int) Math.round(Math.random());
      }
    }

    StringBuilder populationString = new StringBuilder();

    boolean isLastRow = false;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        populationString.append(cells[i][j]);
      }

      if (i == width - 1) {
        isLastRow = true;
      }

      if (!isLastRow) {
        populationString.append("#");
      }
    }

    return populationString.toString();
  }

  private static String findArgByLetter(String argumentLetter) {
    String value = "";

    for (String arg : getArguments()) {
      if (arg.startsWith(argumentLetter)) {
        value = arg;
      }
    }

    if (value.isEmpty()) {
      value = String.valueOf(NO_PROVIDED);
    }

    return value;
  }

  private static int parseIntArg(String argumentLetter) {
    String argValue = findArgByLetter(argumentLetter);

    int number = NO_PROVIDED;

    if (argValue.equals(String.valueOf(NO_PROVIDED))) {
      return number;
    }

    if (!argValue.isEmpty()) {
      String value = argValue.split("=")[1];

      try {
        number = Integer.parseInt(value);
      } catch (NumberFormatException e) {
        number = INVALID;
      }
    }

    return number;
  }

  private static boolean isValidInt(int value, int[] validValues) {
    boolean isValid = false;
    boolean found = false;

    for (int i = 0; i < validValues.length && !found; i++) {
      if (value == validValues[i]) {
        isValid = true;
        found = true;
      }
    }

    return isValid;
  }

  private static String[] getArguments() {
    return arguments;
  }

  public static void log(String message) {
    System.out.println(message);
  }

  public static void logError(String message) {
    System.err.println(message);
  }

  public static void logArguments() {
    String widthFormatted = formatArgumentToPrint("width", width);
    String heightFormatted = formatArgumentToPrint("height", height);
    String generationsFormatted = formatArgumentToPrint("generations", generations);
    String speedFormatted = formatArgumentToPrint("speed", speed);
    String populationFormatted = formatArgumentToPrint("population", population);

    log(widthFormatted);
    log(heightFormatted);
    log(generationsFormatted);
    log(speedFormatted);
    log(populationFormatted);
  }

  public static void logRequirements() {
    logError("Use: w=<int> h=<int> g=<int> s=<int> p=<string> (Order isn't important)");
  }

  public static String formatArgumentToPrint(String argument, int value) {
    if (value == NO_PROVIDED) {
      return argument + " = [No present]";
    } else if (value == INVALID) {
      return argument + " = [Invalid]";
    } else {
      return argument + " = [" + value + "]";
    }
  }

  public static String formatArgumentToPrint(String argument, String value) {
    if (value.equals(String.valueOf(NO_PROVIDED))) {
      return argument + " = [No present]";
    } else if (value.equals(String.valueOf(INVALID))) {
      return argument + " = [Invalid]";
    } else {
      return argument + " = [\"" + value + "\"]";
    }
  }
}