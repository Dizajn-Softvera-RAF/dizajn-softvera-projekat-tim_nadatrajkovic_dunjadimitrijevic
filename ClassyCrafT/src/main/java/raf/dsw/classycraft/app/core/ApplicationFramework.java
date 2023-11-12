package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.log_factory.Logger;
import raf.dsw.classycraft.app.model.log_factory.LoggerFactory;
import raf.dsw.classycraft.app.model.message.MessageGenerator;
import raf.dsw.classycraft.app.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;


    //buduca polja za model celog projekta
    private ClassyRepository classyRepository;

    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;
    private ClassyTreeImplementation classyTree;

    private ApplicationFramework(){

    }

    public void initialize(){

        this.classyRepository = new ClassyRepositoryImplementation();
        System.out.println(this.getClassyRepository());

        loggerFactory = new LoggerFactory();
        Logger loggerConsole= loggerFactory.CreateLogger("consolelogger");

        messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(MainFrame.getInstance());
        messageGenerator.addSubscriber(loggerConsole);
        //messageGenerator.GenerateMessage("proba poruke", MessageType.ERROR);

        classyTree = new ClassyTreeImplementation();
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
            instance.initialize();
        }
        return instance;
    }
    public ClassyRepository getClassyRepository() {
        return this.classyRepository;
    }
    public void setClassyRepository(ClassyRepository classyRepository) {
        this.classyRepository = classyRepository;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public  void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public void setLoggerFactory(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }
}
