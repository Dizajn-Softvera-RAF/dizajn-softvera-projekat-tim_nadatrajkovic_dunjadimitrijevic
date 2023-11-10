package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.log_factory.Logger;
import raf.dsw.classycraft.app.model.log_factory.LoggerFactory;
import raf.dsw.classycraft.app.model.message.MessageGenerator;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.MainFrame;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    public void setClassyRepositoryImplementation(ClassyRepositoryImplementation classyRepositoryImplementation) {
        this.classyRepositoryImplementation = classyRepositoryImplementation;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public void setLoggerFactory(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    //buduca polja za model celog projekta
    private ClassyRepositoryImplementation classyRepositoryImplementation;

    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;

    private ApplicationFramework(){

    }

    public void initialize(){

        MainFrame.getInstance().setVisible(true);
        this.classyRepositoryImplementation = new ClassyRepositoryImplementation();
        System.out.println(this.getClassyRepositoryImplementation());

        loggerFactory = new LoggerFactory();
        Logger loggerConsole= loggerFactory.CreateLogger("consolelogger");

        messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(MainFrame.getInstance());
        messageGenerator.addSubscriber(loggerConsole);
        messageGenerator.GenerateMessage("proba poruke", MessageType.ERROR);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
    public ClassyRepositoryImplementation getClassyRepositoryImplementation() {
        return this.classyRepositoryImplementation;
    }
}
