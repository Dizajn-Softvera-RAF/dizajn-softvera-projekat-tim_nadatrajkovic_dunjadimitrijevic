package raf.dsw.classycraft.app.view;

import jdk.jfr.consumer.RecordedClass;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
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
    private void ukloniIzSelektovanih(ElementPainter ep)
    {
        ep.setSelektovano(false);
        this.selektovaniList.remove(ep);
        //repaint(); // mozda i ne?
    }
    public void ukloniSveIzSelektovanih()
    {
        for (ElementPainter ep:selektovaniList) {
            ep.setSelektovano(false);
        }
        selektovaniList.clear();
        repaint();
    }

    private boolean crtaSeLaso;
    private Rectangle laso;

    private boolean doOverlap(Point l1, Point r1, Point l2, Point r2)
    {
        System.out.println("l1: " + l1+" r1: "+ r1 + ";  l2: "+l2 + " r2: " + r2);
        // if rectangle has area 0, no overlap
        if (l1.x == r1.x || l1.y == r1.y || r2.x == l2.x || l2.y == r2.y)
        {
            System.out.println("prvi uslov");
            return false;
        }

        // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x) {
            System.out.println("drugi uslov");
            return false;
        }

        // If one rectangle is above other
//        if (r1.y > l2.y || r2.y > l1.y) {
//            System.out.println("treci uslov");
//            return false;
//        }

        // If one rectangle is above other
        if (r1.y < l2.y || r2.y < l1.y) {
            System.out.println("treci uslov");
            return false;
        }

        return true;
    }
    public void drawLasso(Point p1, Point p2)
    {
        Point gorelevo;
        Point doledesno;
        if(p1.x < p2.x && p1.y < p2.y)
        {
            gorelevo = p1;
            doledesno = p2;
        }
        else if(p1.x > p2.x && p1.y > p2.y)
        {
            gorelevo = p2;
            doledesno = p1;
        }
        else if(p1.x > p2.x && p1.y < p2.y)
        {
            gorelevo = new Point(p2.x, p1.y);
            doledesno = new Point(p1.x, p2.y);
        }
        else/* if(p1.x < p2.x && p1.y > p2.y)*/
        {
            gorelevo = new Point(p1.x, p2.y);
            doledesno = new Point(p2.x, p1.y);
        }

        int width = doledesno.x - gorelevo.x;
        int height = doledesno.y - gorelevo.y;
        crtaSeLaso = true;
        laso = new Rectangle(gorelevo.x, gorelevo.y,width, height);

        for(ElementPainter ep : this.painterList)
        {
            if(ep instanceof InterclassPainter)
            {
                System.out.println("ep je instanceof InterclassPainter");
                Point l1 = ((Interclass)ep.getDiagramElement()).getPocetnaTacka();
                Point r1 = ((Interclass)ep.getDiagramElement()).getKrajnjaTacka();
                System.out.println("l1: " + l1+" r1: "+ r1);
                if(doOverlap(l1, r1, gorelevo, doledesno))
                {
                    System.out.println("they DO OVERLAP");
                    this.dodajUSelektovane(ep);
                }
                else
                {
                    System.out.println("they DONT overlap");
                    this.ukloniIzSelektovanih(ep);
                }
            }
        }

            //System.out.println("trenutni painter " + ep.getDiagramElement().getName());

            // treba dodati za veze


    }
//        for (ElementPainter ep:painterList) {
//            //if(ep.elementAt(p2))
////            {
////                dv.dodajUSelektovane(ep); // ako je kliknut neki element, dodace ga u listu
////                System.out.println("naso selektovan "+ep.toString());
////                return;
////            }
//        }
//        //g.drawRect(p1.x,p1.y,width ,height);
//        repaint();

    public void removeLasso()
    {
        // trebace posle
        crtaSeLaso = false;
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
//        for (var child:diagram.getChildren()) {
//
//
//        }
        selektovaniList = new ArrayList<>();

        crtaSeLaso = false;

        this.addMouseListener(diagramListener);
        this.addMouseMotionListener(diagramListener);
        repaint();
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
            //iz connection grane pokusad da radi observer
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
          //ovo je bilo pre
            //removeAll();
            //repaint();
            //revalidate();
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
        if(crtaSeLaso)
            g2.draw(laso);
        mouseDrag(g2);

        //ovde crta liniju za veze

    }
//    private void addDiagramChildPainter(DiagramElement de)
//    {
//        ElementPainter ep;
//        if(de instanceof Klasa)
//            ep = new KlasaPainter(de);
////        if(de instanceof Interclass)
////        {
////
////        }
//    }

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
