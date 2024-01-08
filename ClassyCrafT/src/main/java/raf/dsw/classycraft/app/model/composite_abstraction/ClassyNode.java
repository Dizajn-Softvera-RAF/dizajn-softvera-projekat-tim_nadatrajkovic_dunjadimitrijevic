package raf.dsw.classycraft.app.model.composite_abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClassyNodeComposite.class, name = "classyNodeComposite"),
        @JsonSubTypes.Type(value = DiagramElement.class, name = "diagramElement"),
        //@JsonSubTypes.Type(value = Cat.class, name = "cat")
})
public abstract class ClassyNode implements IPublisher {



    @JsonIgnore
    transient List<ISubscriber> subscriberList;

    private String name;
    @JsonIgnore
    transient private ClassyNode parent;

    public List<ISubscriber> getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(List<ISubscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
        subscriberList=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifySubscribers(new Notification(this, NotificationType.RENAME));
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void removeNode()
    {
        //this.notifySubscribers(new Notification(this, NotificationType.DELETE));
        this.parent=null;

        /*for (ISubscriber i:subscriberList) {
            removeSubscriber(i);
        }*/
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClassyNode)
            return this.name.equals(((ClassyNode)obj).getName());
        return false;
    }
}
