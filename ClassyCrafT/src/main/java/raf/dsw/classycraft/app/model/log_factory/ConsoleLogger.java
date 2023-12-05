package raf.dsw.classycraft.app.model.log_factory;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.model.message.Message;

public class ConsoleLogger implements Logger {
    @Override
    public void napraviLogger(Message message) {
        System.out.println(message.toString());

    }

    @Override
    public void Update(Object notification) {
        if(notification instanceof Message)
            napraviLogger((Message)notification);
    }
}
