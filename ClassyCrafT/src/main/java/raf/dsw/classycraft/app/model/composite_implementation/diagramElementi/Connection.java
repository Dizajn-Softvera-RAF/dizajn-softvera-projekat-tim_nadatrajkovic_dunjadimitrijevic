package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

import java.awt.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Agregacija.class, name = "agregacija"),
        @JsonSubTypes.Type(value = Generalizacija.class, name = "generalizacija"),
        @JsonSubTypes.Type(value = Kompozicija.class, name = "kompozicija"),
        @JsonSubTypes.Type(value = Zavisnost.class, name = "zavisnost"),
})
@JsonTypeName("connection")
public abstract class Connection extends DiagramElement implements ISubscriber {
    Point odTacka;
    Point doTacka;


    Interclass InterclassOd;
    Interclass InterclassDo;

    //isto i ovde valjda treba da se smanji konstruktor zbog seijalizacije
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
