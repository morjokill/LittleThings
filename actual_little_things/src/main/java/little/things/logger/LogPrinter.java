package little.things.logger;

import org.apache.log4j.Logger;

public class LogPrinter {
    private static final Logger log = Logger.getLogger(LogPrinter.class);
    public static void main(String[] args) {
        log.info("Look at this beautiful message!");
    }
}
