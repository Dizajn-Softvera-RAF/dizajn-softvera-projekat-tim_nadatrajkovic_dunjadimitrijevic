package raf.dsw.classycraft.app.model.composite_implementation;

import javafx.util.Pair;
import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Project extends ClassyNodeComposite { //implements ISubscriber { mozda je on zapravo publisher na settext

    private String imeAutora;

    private static int brojacProjekata=1;
    public Project(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Project(ClassyNode parent)
    {
        super("projekat"+brojacProjekata,parent);
        imeAutora="";
        brojacProjekata++;
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO hendlovati exceptione
        if (child != null &&  child instanceof Package){
            Package p = (Package) child;
            if (!this.getChildren().contains(p)){
                this.getChildren().add(p);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        //TODO
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
        notifySubscribers(new Notification(this, NotificationType.RENAME));
        System.out.println("promenio ime autora "+this.imeAutora);
    }

}
