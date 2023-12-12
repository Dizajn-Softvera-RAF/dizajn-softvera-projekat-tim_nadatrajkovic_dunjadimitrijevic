package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.controller.OpenPackageAction;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class DiagramView extends JPanel implements ISubscriber {

    //ovde crta dijagram eto
    private Diagram diagram;
    //private Line2D trenutnaLinija=null;
    private Point p1,p2;
    public boolean pomeraMis=false;


//    public Line2D getTrenutnaLinija() {
//        return trenutnaLinija;
//    }
//
//    public void setTrenutnaLinija(Line2D trenutnaLinija) {
//        this.trenutnaLinija = trenutnaLinija;
//    }

    public void setPocetakLinije(Point p)
    {
        p1=p;
    }



    private DiagramViewMouseListener diagramListener;


    public List<ElementPainter> getPainterList() {
        return painterList;
    }

    private List<ElementPainter> painterList;
    private List<ElementPainter> selektovaniList;

    public List<ElementPainter> getSelektovaniList() {
        return selektovaniList;
    }

    public void setSelektovaniList(List<ElementPainter> selektovaniList) {
        this.selektovaniList = selektovaniList;
    }
    public void dodajUSelektovane(ElementPainter ep)
    {
        selektovaniList.add(ep);
        ep.setSelektovano(true);
        repaint();
    }
    public void ukloniSveIzSelektovanih()
    {
        for (ElementPainter ep:selektovaniList) {
            ep.setSelektovano(false);
        }
        selektovaniList.clear();
        repaint();
    }
    private ElementPainter selektovan;

//    public ElementPainter getSelektovan() {
//        return selektovan;
//    }
//
//    public void setSelektovan(ElementPainter selektovan) {
//        if(this.selektovan!=null)
//            this.selektovan.setSelektovano(false);
//        this.selektovan = selektovan;
//        if(this.selektovan!=null)
//            this.selektovan.setSelektovano(true);
//        repaint();
//    }

    public DiagramView(Diagram d) {
        super();
        diagram = d;//ovaj diagram view je subscriber za dijagram jel? pa se apdejtuje kad dodamo nesto na dijagram i repaintuje sve

        diagramListener=new DiagramViewMouseListener(this);
        painterList=new ArrayList<>();
        selektovaniList = new ArrayList<>();

        this.addMouseListener(diagramListener);
        this.addMouseMotionListener(diagramListener);

    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void Update(Object notification) {
        if(notification instanceof Notification)
        {
            Notification n=(Notification) notification;
            if(n.getNotificationType()== NotificationType.DELETE)
            {
                if(n.getObjectOfNotification() instanceof DiagramElement)
                {
                    DiagramElement brisani=(DiagramElement) n.getObjectOfNotification();
                    for (ElementPainter ep:painterList) {
                        if(ep.getDiagramElement().equals(brisani))
                            painterList.remove(ep);
                    }
                }
                //removeAll();
                repaint();
                //revalidate();
            }
        }
    }

    ///* ovo odkomentarisi posle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("uso da peint component");
        Graphics2D g2=(Graphics2D) g;
        int fontSize=15;
        g2.setFont(new Font("TimesNewRoman",Font.PLAIN,fontSize));

        for (ElementPainter p:painterList) {
            p.draw(g2);
        }

        mouseDrag(g2);

        //ovde crta liniju za veze

    }

    public void addPainter(ElementPainter painter)
    {

        painterList.add(painter);
        repaint();
    }

    public void mouseDrag(Graphics2D g)
    {
        if(p1!=null && p2!=null) {
            System.out.println("uso u mousedrag u diagramview da crta trenutnu liniju"+p1.toString()+" "+p2.toString());

            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }
}
