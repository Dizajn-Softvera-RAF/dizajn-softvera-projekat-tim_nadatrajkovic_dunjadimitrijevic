package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber {

    //ovde crta dijagram eto
    Diagram diagram;


    public DiagramView(Diagram d) {
        super();
        diagram = d;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void Update(Object notification) {
        if(notification.toString().equals("brisi"))
        {
            removeAll();
            repaint();
            revalidate();
        }
    }
}
