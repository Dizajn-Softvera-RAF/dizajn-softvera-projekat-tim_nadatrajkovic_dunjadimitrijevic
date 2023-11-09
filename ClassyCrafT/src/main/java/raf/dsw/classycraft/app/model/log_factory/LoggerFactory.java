package raf.dsw.classycraft.app.model.log_factory;

public class LoggerFactory {

    public LoggerFactory() {

    }

    public Logger CreateLogger(String LogType) {

        if(LogType.equalsIgnoreCase("FILELOGGER"))
            return new FileLogger();
        else if(LogType.equalsIgnoreCase("CONSOLELOGGER"))
            return new ConsoleLogger();
        return null;
    }
}
