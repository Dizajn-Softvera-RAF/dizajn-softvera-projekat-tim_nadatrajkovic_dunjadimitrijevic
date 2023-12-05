package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.controller.OpenPackageAction;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class DiagramView extends JPanel implements ISubscriber {

    //ovde crta dijagram eto
    private Diagram diagram;


    private DiagramViewMouseListener diagramListener;


    private List<ElementPainter> painterList;

    public DiagramView(Diagram d) {
        super();
        diagram = d;

        diagramListener=new DiagramViewMouseListener(this);

        this.addMouseListener(diagramListener);

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
            //repaint();
            revalidate();
        }
    }

    ///* ovo odkomentarisi posle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("uso da peint component");
        Graphics2D g2=(Graphics2D) g;

        /*for (ElementPainter p:painterList) {
            p.draw(g);
        }*/

    }



}
