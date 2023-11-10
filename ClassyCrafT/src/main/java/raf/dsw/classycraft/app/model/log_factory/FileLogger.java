package raf.dsw.classycraft.app.model.log_factory;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.model.message.Message;

public class FileLogger implements Logger {
    public FileLogger() {

    }

    @Override
    public void napraviLogger(Message message) {
        //TODO upisi u taj maven fajl stagod

    }

    @Override
    public void Update(Object notification) {
        if(notification instanceof Message)
            napraviLogger((Message) notification);
    }
}
