package Utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jLogger {
    private static final Logger logger = LogManager.getLogger(Log4jLogger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}

