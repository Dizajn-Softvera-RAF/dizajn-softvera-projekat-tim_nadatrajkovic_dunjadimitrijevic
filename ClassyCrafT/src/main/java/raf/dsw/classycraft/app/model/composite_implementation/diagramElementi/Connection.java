package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class Connection extends DiagramElement implements ISubscriber {
    Point odTacka;
    Point doTacka;


    Interclass interclassOd;
    Interclass interclassDo;

    //isto i ovde valjda treba da se smanji konstruktor zbog seijalizacije
    public Connection(String name, ClassyNode parent, Interclass odInterclass,
                      Interclass doInterclass, Point odTacka, Point doTacka) {
        super(name, parent);
        interclassOd =odInterclass;
        interclassOd.addSubscriber(this);
        interclassDo =doInterclass;
        interclassDo.addSubscriber(this);
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

    @Override
    public void setParent(ClassyNode parent) {
        super.setParent(parent);

    }

    public Interclass getInterclassOd() {
        return interclassOd;

    }

    public void setInterclassOd(Interclass interclassOd) {
        this.interclassOd = interclassOd;
        notifySubscribers(new Notification(this, NotificationType.MOVE));
    }

    public Interclass getInterclassDo() {
        return interclassDo;
    }

    public void setInterclassDo(Interclass interclassDo) {
        this.interclassDo = interclassDo;
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
