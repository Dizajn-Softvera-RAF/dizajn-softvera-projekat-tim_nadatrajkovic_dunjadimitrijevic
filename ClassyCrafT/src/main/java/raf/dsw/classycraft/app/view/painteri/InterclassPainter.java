package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;

import java.awt.*;

public abstract class InterclassPainter extends ElementPainter {
    protected int width,height; // negde treba izracunati i proslediti da se cuva i u modelu (jer zavisi od Graphics)

    public InterclassPainter(Interclass interclass)
    {
        super(interclass);
    }
    @Override
    public void draw(Graphics2D g) {
        Point pocetnaTacka = ((Interclass)diagramElement).getPocetnaTacka();

        String ime=diagramElement.getName();

        int velicinaFonta=g.getFont().getSize();

        int maxSize=duzinaReci(ime,g);

        Point koordinatePoljaInterklase=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        Interclass interclass=(Interclass) diagramElement;

        boolean imaAtribut = false, imaMetoda = false;

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

        width=maxSize+2*velicinaFonta;
        ((Interclass) diagramElement).setWidth(width);


        height= koordinatePoljaInterklase.y - pocetnaTacka.y;
        ((Interclass) diagramElement).setHeight(height);

        ((Interclass) diagramElement).setKrajnjaTacka(width, height);

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
        g.setStroke(new BasicStroke(2));
    }

    @Override
    public boolean elementAt(Point p) {
        Point pocetnaTacka = ((Interclass)diagramElement).getPocetnaTacka();
        if((p.x> pocetnaTacka.x && p.x< pocetnaTacka.x+width) && (p.y> pocetnaTacka.y && p.y< pocetnaTacka.y+height))
            return true;
        return false;
    }
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
