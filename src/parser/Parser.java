package parser;

import data.ArgumentRequirements;

public class Parser {

    public void parse(String[] args) {
        int width = parseWidth(args);
        ArgumentRequirements.getInstance().setWidth(width);

        int height = parseHeight(args);
        ArgumentRequirements.getInstance().setHeight(height);

        int generations = parseGenerations(args);
        ArgumentRequirements.getInstance().setGenerations(generations);

        int speed = parseSpeed(args);
        ArgumentRequirements.getInstance().setSpeed(speed);

        String population = parsePopulation(args);
        ArgumentRequirements.getInstance().setPopulation(population);
    }

    private int parseWidth(String[] args) {
        int width = parseIntArg(args, "w");
        return width;
    }

    private int parseHeight(String[] args) {
        int height = parseIntArg(args, "h");
        return height;
    }

    private int parseGenerations(String[] args) {
        int generations = parseIntArg(args, "g");
        return generations;
    }

    private int parseSpeed(String[] args) {
        int speed = parseIntArg(args, "s");
        return speed;
    }

    private String parsePopulation(String[] args) {
        return "population";
    }

    private String parseArg(String[] args, String arg) {
        return "";
    }

    private int parseIntArg(String[] args, String arg) {
        String value = parseArg(args, arg);

        if (value.isEmpty()) {
            return -1;
        }

        return Integer.parseInt(value.substring(2));
    }

}
