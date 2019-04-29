package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBLogger {
    private static final Logger logger = LogManager.getLogger(DBLogger.class);

    public static void update(String compName, Integer points) {
        logger.traceEntry("Competitor " + compName + " has now + " + points);
    }

    public static void selectOne(String refName) {
        logger.traceEntry("Competitor " + refName + " has been found");
    }
}
