package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite {
    public static int brojacPaketa=1;
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Package(ClassyNode parent)
    {
        super("package"+ brojacPaketa,parent);
        brojacPaketa++;
    }

    @Override
    public void addChild(ClassyNode child) {
        //exceptioni?
        if (child != null &&  child instanceof Package){
            Package p = (Package) child;
            if (!this.getChildren().contains(p)){
                this.getChildren().add(p);
            }
        }
        else if (child != null &&  child instanceof Diagram){
            Diagram d = (Diagram) child;
            if (!this.getChildren().contains(d)){
                this.getChildren().add(d);
            }
            System.out.println(this.getSubscriberList());
            this.notifySubscribers(new Notification(d, NotificationType.ADD));

        }
        // notifySubscribers() ...
        //addSubscriber(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        //removeSubscriber(child);
        this.getChildren().remove(child);
        notifySubscribers(new Notification(child, NotificationType.DELETE));
    }

//    public void openPackage()
//    {
//
//    }

}
