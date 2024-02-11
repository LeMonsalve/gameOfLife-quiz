package data;

public class ArgumentRequirements {
    private static ArgumentRequirements instance;

    private int width = -1;
    private int height = -1;
    private int generations = -1;
    private int speed = -1;
    private String population = "";

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
}
