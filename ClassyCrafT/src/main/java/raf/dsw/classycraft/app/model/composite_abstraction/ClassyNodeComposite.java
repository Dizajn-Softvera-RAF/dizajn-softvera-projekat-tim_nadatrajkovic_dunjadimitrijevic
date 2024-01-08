package raf.dsw.classycraft.app.model.composite_abstraction;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.composite_implementation.Package;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Diagram.class, name = "diagram"),
        @JsonSubTypes.Type(value = Package.class, name = "paket"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "projectExplorer"),
})
@JsonTypeName("classyNodeComposite")
public abstract class ClassyNodeComposite extends ClassyNode /*implements ISubscriber*/ {


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


    public void setChildren(ArrayList<ClassyNode> children) {
        this.children = children;
    }

    public abstract void addChild(ClassyNode child);
    public void removeChild(ClassyNode child)
    {
        this.getChildren().remove(child);
    }


}
