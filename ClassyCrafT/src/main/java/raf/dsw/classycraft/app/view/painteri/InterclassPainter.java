package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;

import java.awt.*;

public abstract class InterclassPainter extends ElementPainter {
    //private Point pocetnaTacka;

    //protected ArrayList<Point> connectionPoints;  //nekako da cuva koji su zauzeti da ne pravi veze u istoj tacki
    protected int width,height; // negde treba izracunati i proslediti da se cuva i u modelu (jer zavisi od Graphics)


//    public InterclassPainter(DiagramElement diagramElement) {
//        super(diagramElement);
//    }

    public InterclassPainter(Interclass interclass)
    {
        super(interclass);
        //pocetnaTacka = interclass.getPocetnaTacka();
        //connectionPoints = new ArrayList<>();
    }

//    public InterclassPainter(DiagramElement diagramElement, Point p) {
//        super(diagramElement);
//        pocetnaTacka = p;
//        connectionPoints=new ArrayList<>();
//    }
//    public Point getPocetnaTacka()
//    {
//        return pocetnaTacka;
//    }
//    public Point getKrajnjaTacka()
//    {
//        return new Point(pocetnaTacka.x + width, pocetnaTacka.y + height);
//    }


//    @Override
//    public void Update(Object notification) {
//        if(notification instanceof Notification)
//        {
//            if(((Notification) notification).getObjectOfNotification() instanceof Interclass)
//            {
//                if(((Notification) notification).getNotificationType() == NotificationType.MOVE)
//                {
//                    this.draw();
//                }
//            }
//        }
//    }

    @Override
    public void draw(Graphics2D g) {
        Point pocetnaTacka = ((Interclass)diagramElement).getPocetnaTacka();

        String ime=diagramElement.getName();

        int velicinaFonta=g.getFont().getSize();

        int maxSize=duzinaReci(ime,g);
        //System.out.println("maxSize poc"+maxSize);

        Point koordinatePoljaInterklase=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        //if(diagramElement instanceof Interclass)
        //{
        Interclass interclass=(Interclass) diagramElement;

        boolean imaAtribut = false, imaMetoda = false;
        //int linijaY = pocetnaTacka.y;

        for (ClassContent c: interclass.getSadrzaj()) {

            if (c instanceof Atribut)
            {
                g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                koordinatePoljaInterklase.y += velicinaFonta;
                maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));
                imaAtribut=true;
            }
        }
        if(imaAtribut)
        {
            koordinatePoljaInterklase.y+=velicinaFonta;
            //linijaY= koordinatePoljaInterklase.y+velicinaFonta/2;
        }

        for (ClassContent c: interclass.getSadrzaj()) {

            if(c instanceof Metoda)
            {
                g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                koordinatePoljaInterklase.y += velicinaFonta;
                maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));
                //imaMetoda=true;
            }

        }

//        if(imaMetoda && imaAtribut)
//        {
//            g.drawLine(pocetnaTacka.x, linijaY, pocetnaTacka.x+width,linijaY);
//        }

        for (ClassContent c: interclass.getSadrzaj()) {

            if (c instanceof ClanEnumeracije)
            {
                g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                koordinatePoljaInterklase.y += velicinaFonta;
                maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));

            }
        }

       //}


        width=maxSize+2*velicinaFonta;
        ((Interclass) diagramElement).setWidth(width);

        //System.out.println("maxsize i width "+maxSize+" "+width);

        height= koordinatePoljaInterklase.y - pocetnaTacka.y;
        ((Interclass) diagramElement).setHeight(height);

        ((Interclass) diagramElement).setKrajnjaTacka(width, height);

//        ((Interclass) diagramElement).setWidth(width); // todo - pitanje da li nam treba ovo...
//        ((Interclass) diagramElement).setHeight(height);

        g.drawString(ime,pocetnaTacka.x+(width-duzinaReci(ime,g))/2, pocetnaTacka.y+velicinaFonta);



        if(selektovano)
        {
            Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{9}, 0);
            g.setStroke(dashed);
        }
        else
        {
            Stroke obicna=new BasicStroke(2);
            g.setStroke(obicna);
        }
        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,width,height);
        //g.setColor(Color.white);
        //g.fillRect(pocetnaTacka.x, pocetnaTacka.y, width, height);
        g.setStroke(new BasicStroke(2));

        //dodajConnectonPoints();

    }

    @Override
    public boolean elementAt(Point p) {
        Point pocetnaTacka = ((Interclass)diagramElement).getPocetnaTacka();
        if((p.x> pocetnaTacka.x && p.x< pocetnaTacka.x+width) && (p.y> pocetnaTacka.y && p.y< pocetnaTacka.y+height))
            return true;
        return false;
    }

//    private void dodajConnectonPoints()
//    {
//        Point pocetnaTacka = ((Interclass)diagramElement).getPocetnaTacka();
//        connectionPoints.clear();
//        int xSkok=width/4;
//        int ySkok=height/4;
//
//        for(int i=0;i<=4;i++)
//        {
//            for(int j=0;j<=4;j++)
//            {
//                if(!(j==1 && i==1))
//                    connectionPoints.add(new Point(pocetnaTacka.x+i*xSkok,pocetnaTacka.y+j*ySkok));
//            }
//        }
//    }

//    public ArrayList<Point> getConnectionPoints() {
//        return connectionPoints;
//    }
    protected int duzinaReci(String rec, Graphics2D g)
    {
        int duzina=0;
        for(int i=0;i<rec.length();i++) {
            int jedan=g.getFontMetrics().charWidth(rec.charAt(i));
            duzina+=jedan;
        }
        return duzina;
    }
}
