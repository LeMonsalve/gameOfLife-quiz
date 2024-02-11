package logger;

import data.ArgumentRequirements;
import helpers.Helpers;

public class GameOfLifeLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.err.println(message);
    }

    @Override
    public void logArguments() {
        int width = ArgumentRequirements.getInstance().getWidth();
        int height = ArgumentRequirements.getInstance().getHeight();
        int generations = ArgumentRequirements.getInstance().getGenerations();
        int speed = ArgumentRequirements.getInstance().getSpeed();
        String population = ArgumentRequirements.getInstance().getPopulation();

        String widthFormatted = Helpers.formatArgument("width", width);
        String heightFormatted = Helpers.formatArgument("height", height);
        String generationsFormatted = Helpers.formatArgument("generations", generations);
        String speedFormatted = Helpers.formatArgument("speed", speed);
        String populationFormatted = Helpers.formatArgument("population", population);

        log(widthFormatted);
        log(heightFormatted);
        log(generationsFormatted);
        log(speedFormatted);
        log(populationFormatted);
    }

    @Override
    public void logRequirements() {
        error("Use: w=<int> h=<int> g=<int> s=<int> p=<string> (Order isn't important)");
    }
}
