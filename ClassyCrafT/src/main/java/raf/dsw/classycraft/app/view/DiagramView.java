package raf.dsw.classycraft.app.view;

import jdk.jfr.consumer.RecordedClass;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.commandPattern.CommandManager;
import raf.dsw.classycraft.app.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.painteri.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DiagramView extends JPanel implements ISubscriber {

    //ovde crta dijagram eto
    private Diagram diagram;

    private CommandManager commandManager;
    //private Line2D trenutnaLinija=null;
    private Point p1,p2;
    public boolean pomeraMis=false;
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer = false;

    private double xOffset = 0;
    private double yOffset = 0;

    private JScrollPane s= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    private AffineTransform at=null;

    public AffineTransform getAf() {
        return at;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        if(zoomFactor < 1.5 && zoomFactor > 0.5)
            this.zoomFactor = zoomFactor;
    }

    public double getPrevZoomFactor() {
        return prevZoomFactor;
    }

    public void setPrevZoomFactor(double prevZoomFactor) {
        this.prevZoomFactor = prevZoomFactor;
    }

    public boolean isZoomer() {
        return zoomer;
    }

    public void setZoomer(boolean zoomer) {
        this.zoomer = zoomer;
    }

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
        if(!selektovaniList.contains(ep))
        {
            selektovaniList.add(ep);
            ep.setSelektovano(true);
        }
        repaint();
    }
    private void ukloniIzSelektovanih(ElementPainter ep)
    {
        ep.setSelektovano(false);
        this.selektovaniList.remove(ep);
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
            else if(ep instanceof ConnectionPainter)
            {
                Connection linija = (Connection)ep.getDiagramElement();
                if(doOverlap(linija.getOdTacka(),linija.getDoTacka(), gorelevo, doledesno))
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

        //this.setAutoscrolls(true);

        commandManager=new CommandManager();

        diagramListener=new DiagramViewMouseListener(this);
        painterList=new ArrayList<>();
//        for (var child:diagram.getChildren()) {
//
//
//        }
        selektovaniList = new ArrayList<>();

        crtaSeLaso = false;

        //JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
//        s.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
//        ///s.setViewportView(this);
//
//        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//        //setPreferredSize(new Dimension(this.getSize().width,this.getSize().height));
//        //
//        // s.setBounds(0,0, 200,200);
//
//        s.setVisible(true);
//
//
//        setLayout(null);
//        add(s);
//        s.setViewportView(MainFrame.getInstance().getPackageView());

        System.out.println("napravilo scroll" + s.toString());

        //scrollPane.createHorizontalScrollBar();
        //scrollPane.createVerticalScrollBar();
        //scrollPane.setPreferredSize(this.getSize());

//        JScrollPane scrollPane = new JScrollPane(this);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        this.setBounds(0, 0, this.getWidth(), this.getHeight());
//        scrollPane.

        this.addMouseListener(diagramListener);
        this.addMouseMotionListener(diagramListener);

        if(painterList.isEmpty())
            createElements();

        repaint();
    }

    private void createElements()
    {
        for(var child : diagram.getChildren())
        {
            if(child instanceof Interclass) {
                var interclass = (Interclass) child;
                InterclassPainter ip = null;
                boolean created = false;
                for (ElementPainter peinter : painterList)
                {
                    if(peinter.getDiagramElement().getName().equals(interclass.getName()))
                    {
                        ip = (InterclassPainter)peinter;
                        //ip.addSubscriber(this);
                        created = true;
                        break;
                    }
                }
                if(!created)
                {
                    if(child instanceof Klasa)
                    {
                        ip = new KlasaPainter((Klasa)child);
                        //child.addSubscriber(ip); //treba : child.addSubscriber(this);
                        //this.addPainter(ip);
                        //painterList.add(ip);
                    }
                    if(child instanceof Interfejs)
                    {
                        ip = new InterfejsPainter((Interfejs)child);
                        //child.addSubscriber(ip);
                        //this.addPainter(ip);
                        //painterList.add(ip);
                    }
                    if(child instanceof Enumeracija)
                    {
                        ip = new EnumeracijaPainter((Enumeracija)child);
                        //child.addSubscriber(ip);
                        //this.addPainter(ip);
                        //painterList.add(ip);
                    }
                    if (ip != null) {
                        this.addPainter(ip);
                        //ip.addSubscriber(this);
                    }
                    child.addSubscriber(this);
                }
            }

            if(child instanceof Connection) {
                var connection = (Connection) child;
                ConnectionPainter cp = null;
                boolean created = false;
                for (ElementPainter peinter : painterList)
                {
                    if(peinter.getDiagramElement()==connection)
                    {
                        cp = (ConnectionPainter) peinter;
                        //cp.addSubscriber(this);
                        created = true;
                        break;
                    }
                }
                if(!created)
                {
                    if(child instanceof Agregacija)
                    {
                        cp = new AgregacijaPainter((Agregacija)child);
                        //child.addSubscriber(cp);
                        //this.addPainter(ip);
                        //painterList.add(cp);
                    }
                    if(child instanceof Kompozicija)
                    {
                        cp = new KompozicijaPainter((Kompozicija)child);
                        //child.addSubscriber(cp);
                        //this.addPainter(ip);
                        //painterList.add(cp);
                    }
                    if(child instanceof Generalizacija)
                    {
                        cp = new GeneralizacijaPainter((Generalizacija)child);
                        //child.addSubscriber(cp);
                        //this.addPainter(ip);
                        //painterList.add(cp);
                    }
                    if(child instanceof Zavisnost)
                    {
                        cp = new ZavisnostPainter((Zavisnost)child);
                        //child.addSubscriber(cp);
                        //this.addPainter(ip);
                        //painterList.add(cp);
                    }
                    if (cp != null) {
                        this.addPainter(cp);
                        //cp.addSubscriber(this);
                    }
                    child.addSubscriber(this);
                }
            }
        }

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

                    Notification nt= new Notification(brisani,NotificationType.DELETE);
                    brisani.removeSubscriber(this);
                    brisani.notifySubscribers(nt);
                    /*for (ElementPainter ep:painterList) {
                        if(ep.getDiagramElement().equals(brisani))
                            painterList.remove(ep);
                    }*/
                }

                if(n.getObjectOfNotification() instanceof ElementPainter)
                {
                    removePainter((ElementPainter) n.getObjectOfNotification());
                }
                //removeAll();
                repaint();
                //revalidate();
            }
            if(n.getNotificationType() == NotificationType.MOVE)
            {
                repaint();
            }
          //ovo je bilo pre
            //removeAll();
            //repaint();
            //revalidate();
        }
    }

    public void saveDiagramAsImage(String filePath) {
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try{
            ImageIO.write(image, "png", new File(filePath));
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("dijagram je sacuvan kao slika, putanja do fajla: " + filePath, MessageType.INFO);
            //System.out.println("dijagram je sacuvan kao slika, putanja do fajla: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///* ovo odkomentarisi posle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //s.paint(g);
        System.out.println("uso da peint component");
        Graphics2D g2=(Graphics2D) g;

        // todo srediti
//        double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
//        double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
//
//        double zoomDiv = zoomFactor / prevZoomFactor;
//
//        xOffset = (zoomDiv) * (xOffset) + (1-zoomDiv) * xRel;
//        yOffset = (zoomDiv) * (yOffset) + (1-zoomDiv) * yRel;

        at = new AffineTransform();

        at.scale(zoomFactor, zoomFactor);

        prevZoomFactor = zoomFactor;

       // at.translate(xOffset,yOffset);
        //g2.transform(at);
        g2.transform(at);

        //this.setSize(new Dimension((int) (this.getWidth()*zoomFactor), (int) (this.getHeight()*zoomFactor)));



        //zoomer = false;
//        if (zoomer) {
//            AffineTransform at = new AffineTransform();
//            at.scale(zoomFactor, zoomFactor);
//            prevZoomFactor = zoomFactor;
//            g2.transform(at);
//            zoomer = false;
//        }
        int fontSize=15;
        g2.setFont(new Font("TimesNewRoman",Font.PLAIN,fontSize));

        for (ElementPainter p:painterList) {
            p.draw(g2);
        }
        if(crtaSeLaso)
            g2.draw(laso);
        mouseDrag(g2);

        //ovde crta liniju za veze

        commandManager.dalMozeRedo();
        commandManager.dalMozeUndo();

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
        //jel se ovde dodaje command u manager



        painterList.add(painter);
        painter.addSubscriber(this);
        repaint();
    }

    public void removePainter(ElementPainter painter)
    {
        /*if(!painterList.contains(painter))
        {
            for (ElementPainter ep:painterList) {

                if(painter.getDiagramElement().equals(ep.getDiagramElement()))
                {
                    painter=ep;
                }
            }
        }
*/
        painterList.remove(painter);



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

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
