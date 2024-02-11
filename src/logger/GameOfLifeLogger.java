package logger;

import data.ArgumentRequirements;

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

        log("width = [" + (width == -1 ? "No present" : width == -2 ? "Invalid" : width) + "]");
        log("height = [" + (height == -1 ? "No present" : height == -2 ? "Invalid" : height) + "]");
        log("generations = [" + (generations == -1 ? "No present" : generations == -2 ? "Invalid" : generations) + "]");
        log("speed = [" + (speed == -1 ? "No present" : speed == -2 ? "Invalid" : speed) + "]");
        log("population = [" + (population.equals("-1") ? "No present" : population.equals("-2") ? "Invalid" : population) + "]");
    }

    @Override
    public void logRequirements() {
        error("Use: w=<int> h=<int> g=<int> s=<int> p=<string> (Order isn't important)");
    }
}
