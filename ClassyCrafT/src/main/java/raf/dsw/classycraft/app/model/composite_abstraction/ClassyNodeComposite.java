package raf.dsw.classycraft.app.model.composite_abstraction;


import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;

import java.util.ArrayList;

public abstract class ClassyNodeComposite extends ClassyNode{
    private ArrayList<ClassyNode> children;

    public ArrayList<ClassyNode> getChildren() {
        return children;
    }

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    @Override
    public void removeNode() {

        for (ClassyNode node:children) {
            node.removeNode();
            this.notifySubscribers(new Notification(this, NotificationType.DELETE));
        }

        super.removeNode();

    }



    public abstract void addChild(ClassyNode child);
    public void removeChild(ClassyNode child)
    {
        this.getChildren().remove(child);
    }
}
