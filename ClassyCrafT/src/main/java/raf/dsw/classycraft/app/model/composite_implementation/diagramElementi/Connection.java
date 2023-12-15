package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

import java.awt.*;

public abstract class Connection extends DiagramElement implements ISubscriber {
    Point odTacka;
    Point doTacka;
    Interclass InterclassOd;
    Interclass InterclassDo;
    public Connection(String name, ClassyNode parent, Interclass odInterclass, Interclass doInterclass, Point odTacka, Point doTacka) {
        super(name, parent);
        InterclassOd=odInterclass;
        odInterclass.addSubscriber(this);
        InterclassDo=doInterclass;
        doInterclass.addSubscriber(this);
        this.odTacka = odTacka;
        this.doTacka = doTacka;
    }

    public Point getOdTacka() {
        return odTacka;
    }

    public void setOdTacka(Point odTacka) {
        this.odTacka = odTacka;
        //notifySubscribers(new Notification(this, NotificationType.MOVE));
    }

    public Point getDoTacka() {
        return doTacka;
    }

    public void setDoTacka(Point doTacka) {
        this.doTacka = doTacka;
    }

    public Interclass getInterclassOd() {
        return InterclassOd;

    }

    public void setInterclassOd(Interclass interclassOd) {
        InterclassOd = interclassOd;
        notifySubscribers(new Notification(this, NotificationType.MOVE));
    }

    public Interclass getInterclassDo() {
        return InterclassDo;
    }

    public void setInterclassDo(Interclass interclassDo) {
        InterclassDo = interclassDo;
        notifySubscribers(new Notification(this, NotificationType.MOVE));
    }

    @Override
    public void Update(Object notification) {
        Notification n = (Notification) notification;
        if(n.getNotificationType() == NotificationType.MOVE)
        {
            if(n.getObjectOfNotification() == this.getInterclassOd())
            {
                this.setInterclassOd((Interclass) n.getObjectOfNotification());
            }
            if(n.getObjectOfNotification() == this.getInterclassDo())
            {
                this.setInterclassDo((Interclass) n.getObjectOfNotification());
            }
        }

    }
}
