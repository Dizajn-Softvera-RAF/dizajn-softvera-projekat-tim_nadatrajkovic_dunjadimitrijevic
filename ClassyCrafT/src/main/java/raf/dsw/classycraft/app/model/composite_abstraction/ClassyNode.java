package raf.dsw.classycraft.app.model.composite_abstraction;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNode implements IPublisher {
    List<ISubscriber> subscriberList;

    private String name;
    private ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    public ClassyNode getParent() {
        return parent;
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
        if (notification == null || this.subscriberList == null || this.subscriberList.isEmpty())
            return;

        for (ISubscriber listener : subscriberList) {
            listener.Update(notification);
        }
    }
}
