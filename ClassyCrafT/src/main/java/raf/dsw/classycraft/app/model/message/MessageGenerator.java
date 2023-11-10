package raf.dsw.classycraft.app.model.message;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    List<ISubscriber> subscriberList;

    public MessageGenerator() {
        this.subscriberList = new ArrayList<>();
    }

    public void GenerateMessage(String tekst, MessageType tip)
    {
        Message msg = new Message(tekst,tip);
        notifySubscribers(msg);

    }



    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscriberList ==null)
            this.subscriberList = new ArrayList<>();
        if(this.subscriberList.contains(sub))
            return;
        this.subscriberList.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscriberList == null || !this.subscriberList.contains(sub))
            return;
        this.subscriberList.remove(sub);

    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscriberList == null || this.subscriberList.isEmpty())
            return;

        for(ISubscriber listener : subscriberList){
            listener.Update(notification);
        }
    }
}
