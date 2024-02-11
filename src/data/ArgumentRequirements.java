package data;

public class ArgumentRequirements {
    private static ArgumentRequirements instance;

    private int width = ArgumentErrorTypes.NO_PROVIDED;
    private int height = ArgumentErrorTypes.NO_PROVIDED;
    private int generations = ArgumentErrorTypes.NO_PROVIDED;
    private int speed = ArgumentErrorTypes.NO_PROVIDED;
    private String population = String.valueOf(ArgumentErrorTypes.NO_PROVIDED);

    private String[] arguments = {};

    private ArgumentRequirements() {}

    public synchronized static ArgumentRequirements getInstance() {
        if (instance == null) {
            instance = new ArgumentRequirements();
        }

        return instance;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
