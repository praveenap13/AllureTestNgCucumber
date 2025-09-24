package utility;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.File;
import java.io.FileInputStream;

    public class LoggerTest {

        private static ThreadLocal<Logger> threadLogger = new ThreadLocal<>();
        private static ThreadLocal<String> threadLogFile = new ThreadLocal<>();

        public static void initLogger(String scenarioName) {
            try {
                String logFileName = "logs/" + scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + ".log";
                threadLogFile.set(logFileName);

                LoggerContext context = (LoggerContext) LogManager.getContext(false);
                Configuration config = context.getConfiguration();

                PatternLayout layout = PatternLayout.newBuilder()
                        .withPattern("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %msg%n")
                        .build();

                FileAppender appender = FileAppender.newBuilder()
                        .withFileName(logFileName)
                        .withName("FileAppender_" + scenarioName)
                        .withLayout(layout)
                        .setConfiguration(config)
                        .build();

                appender.start();
                config.addAppender(appender);
                config.getRootLogger().addAppender(appender, null, null);
                context.updateLoggers();

                threadLogger.set(LogManager.getLogger(scenarioName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void logStep(String message) {
            threadLogger.get().info(message);
            Allure.step(message);
        }

        public static void info(String message) { logStep("[INFO] " + message); }
        public static void warn(String message) { logStep("[WARN] " + message); }
        public static void error(String message) { logStep("[ERROR] " + message); }

        public static void attachLogFile() {
            try {
                String logFile = threadLogFile.get();
                if (logFile != null) {
                    Allure.addAttachment("Scenario Log", new FileInputStream(new File(logFile)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

