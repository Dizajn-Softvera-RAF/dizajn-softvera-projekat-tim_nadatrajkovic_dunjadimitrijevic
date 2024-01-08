package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ElementPainter implements ISubscriber, IPublisher {
    //diagram
    List<ISubscriber> subscriberList;
    protected DiagramElement diagramElement;
    protected boolean selektovano=false;


    public ElementPainter(DiagramElement diagramElement) {
        this.diagramElement = diagramElement;
        subscriberList=new ArrayList<>();
    }

    public abstract void draw(Graphics2D g);
    public abstract boolean elementAt(Point P);

    public boolean isSelektovano() {
        return selektovano;
    }

    public void setSelektovano(boolean selektovano) {
        this.selektovano = selektovano;
    }

    public DiagramElement getDiagramElement() {
        return diagramElement;
    }

    @Override
    public void Update(Object notification) {
        if(notification instanceof Notification)
        {
            Notification n =(Notification) notification;
            if(n.getNotificationType()== NotificationType.DELETE)
            {
                notifySubscribers(new Notification(this,NotificationType.DELETE));
            }
        }
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        subscriberList.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber sub:subscriberList) {
            sub.Update(notification);
        }
    }
}
