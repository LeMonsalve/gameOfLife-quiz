package parser;

import data.ArgumentErrorTypes;
import data.ArgumentRequirements;
import data.ArgumentValidations;
import logger.Logger;

import java.util.Arrays;

public class Parser {
    private final Logger logger;

    public Parser(String[] args, Logger logger) {
        ArgumentRequirements.getInstance().setArguments(args);
        this.logger = logger;
    }

    public void parse() {
        int width = parseWidth();
        ArgumentRequirements.getInstance().setWidth(width);

        int height = parseHeight();
        ArgumentRequirements.getInstance().setHeight(height);

        int generations = parseGenerations();
        ArgumentRequirements.getInstance().setGenerations(generations);

        int speed = parseSpeed();
        ArgumentRequirements.getInstance().setSpeed(speed);

        String population = parsePopulation();
        ArgumentRequirements.getInstance().setPopulation(population);
    }

    private int parseWidth() {
        int width = parseIntArg("w");

        if (width == ArgumentErrorTypes.NO_PROVIDED) return width;

        int[] validWidths = ArgumentValidations.getInstance().getValidWidths();

        boolean isValid = isValidInt(width, validWidths);

        if (!isValid) width = ArgumentErrorTypes.NO_VALID;

        return width;
    }

    private int parseHeight() {
        int height = parseIntArg("h");

        if (height == ArgumentErrorTypes.NO_PROVIDED) return height;

        int[] validHeights = ArgumentValidations.getInstance().getValidHeights();
        boolean isValid = isValidInt(height, validHeights);

        if (!isValid) height = ArgumentErrorTypes.NO_VALID;

        return height;
    }

    private int parseGenerations() {
        int generations = parseIntArg("g");
        return generations;
    }

    private int parseSpeed() {
        int speed = parseIntArg("s");
        return speed;
    }

    private String parsePopulation() {
        int width = ArgumentRequirements.getInstance().getWidth();
        int height = ArgumentRequirements.getInstance().getHeight();

        String population = findArgByLetter("p");
        String populationValue;

        try {
            populationValue = population.split("=")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            population = String.valueOf(ArgumentErrorTypes.NO_PROVIDED);
            return population;
        }

        if (width == -1 || width == -2) {
            logger.error("Population is invalid because width is invalid or not present");
            population = String.valueOf(ArgumentErrorTypes.NO_VALID);
            return population;
        }

        if (height == -1 || height == -2) {
            logger.error("Population is invalid because height is invalid or not present");
            population = String.valueOf(ArgumentErrorTypes.NO_VALID);
            return population;
        }

        if (populationValue.startsWith("#")) {
            population = String.valueOf(ArgumentErrorTypes.NO_VALID);
            return population;
        }

        String[] populationLines = populationValue.split("#");
        int firstRowLength = populationLines[0].length();

        for (String line : populationLines) {
            if (line.length() != firstRowLength) {
                population = String.valueOf(ArgumentErrorTypes.NO_VALID);
                return population;
            }
        }

        int populationRows = populationValue.split("#").length;
        int populationCols = populationValue.split("#")[0].length();

        if (populationRows > height || populationCols > width) {
            population = String.valueOf(ArgumentErrorTypes.NO_VALID);
            return population;
        }

        population = populationValue;

        return population;
    }

    private String findArgByLetter(String argumentLetter) {
        String value = "";

        for (String arg : getArguments()) {
            if (arg.startsWith(argumentLetter)) {
                value = arg;
            }
        }

        if (value.isEmpty()) {
            value = String.valueOf(ArgumentErrorTypes.NO_PROVIDED);
        }

        return value;
    }

    private int parseIntArg(String argumentLetter) {
        String argValue = findArgByLetter(argumentLetter);

        int number = ArgumentErrorTypes.NO_PROVIDED;

        if (argValue.equals(String.valueOf(ArgumentErrorTypes.NO_PROVIDED))) {
            return number;
        }

        if (!argValue.isEmpty()) {
            String value = argValue.split("=")[1];

            try {
                number = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                number = ArgumentErrorTypes.NO_VALID;
            }
        }

        return number;
    }

    private boolean isValidInt(int value, int[] validValues) {
        boolean isValid = Arrays.stream(validValues).anyMatch(validValue -> value == validValue);
        return isValid;
    }

    private String[] getArguments() {
        return ArgumentRequirements.getInstance().getArguments();
    }

}
