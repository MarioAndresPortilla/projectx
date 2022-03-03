package org.ea.main;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.ea.appenders.MyAppender;


public class Log4JExample {
    static final Logger logger = Logger.getLogger(Log4JExample.class);

    public static void main(String[] args) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
        Logger.getRootLogger().addAppender(new MyAppender());

        String TestString = "Test String";
        logger.info("Log4J Test  " + TestString + ")",
                new Exception()
        );


        try {

        }catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
    }
}
