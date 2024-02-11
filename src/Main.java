import configuration.Configuration;
import logger.GameOfLifeLogger;
import logger.Logger;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        int[] validWidths = new int[] {2, 3, 10, 20, 40, 80};
        int[] validHeights = new int[] {2, 3, 10, 20, 40};

        Configuration.withValidWidths(validWidths);
        Configuration.withValidHeights(validHeights);

        Logger logger = new GameOfLifeLogger();

        Parser parser = new Parser(args, logger);
        parser.parse();

        if (args.length < 1) logger.logRequirements();

        logger.logArguments();
    }
}