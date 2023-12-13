package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterclassPainter extends ElementPainter {
    protected Point pocetnaTacka;

    protected ArrayList<Point> connectionPoints;  //nekako da cuva koji su zauzeti da ne pravi veze u istoj tacki
    protected int width,height;


    public InterclassPainter(DiagramElement diagramElement) {
        super(diagramElement);
    }

    public InterclassPainter(DiagramElement diagramElement, Point p) {
        super(diagramElement);
        pocetnaTacka = p;
        connectionPoints=new ArrayList<>();
    }
    public Point getPocetnaTacka()
    {
        return pocetnaTacka;
    }
    public Point getKrajnjaTacka()
    {
        return new Point(pocetnaTacka.x + width, pocetnaTacka.y + height);
    }

    @Override
    public void draw(Graphics2D g) {

        String ime=diagramElement.getName();

        int velicinaFonta=g.getFont().getSize();

        int maxSize=duzinaReci(ime,g);
        //System.out.println("maxSize poc"+maxSize);

        Point koordinatePoljaInterklase=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        if(diagramElement instanceof Interclass)
        {
            Interclass interclass=(Interclass) diagramElement;

            boolean imaAtribut=false,imaMetoda=false;
            int linijaY= pocetnaTacka.y;

            for (ClassContent c: interclass.getClassContent()) {

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
                linijaY= koordinatePoljaInterklase.y+velicinaFonta/2;
            }

            for (ClassContent c: interclass.getClassContent()) {

                if(c instanceof Metoda)
                {
                    g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                    koordinatePoljaInterklase.y += velicinaFonta;
                    maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));
                    imaMetoda=true;
                }

            }

            if(imaMetoda && imaAtribut)
            {
                g.drawLine(pocetnaTacka.x, linijaY, pocetnaTacka.x+width,linijaY);
            }

            for (ClassContent c: interclass.getClassContent()) {

                if (c instanceof ClanEnumeracije)
                {
                    g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                    koordinatePoljaInterklase.y += velicinaFonta;
                    maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));

                }
            }

        }


        width=maxSize+2*velicinaFonta;

        //System.out.println("maxsize i width "+maxSize+" "+width);

        height= koordinatePoljaInterklase.y - pocetnaTacka.y;

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
        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,width ,height);
        g.setStroke(new BasicStroke(2));

        dodajConnectonPoints();

    }

    @Override
    public boolean elementAt(Point p) {
        if((p.x> pocetnaTacka.x && p.x< pocetnaTacka.x+width) && (p.y> pocetnaTacka.y && p.y< pocetnaTacka.y+height))
            return true;
        return false;
    }

    private void dodajConnectonPoints()
    {
        connectionPoints.clear();
        int xSkok=width/4;
        int ySkok=height/4;

        for(int i=0;i<=4;i++)
        {
            for(int j=0;j<=4;j++)
            {
                if(!(j==1 && i==1))
                    connectionPoints.add(new Point(pocetnaTacka.x+i*xSkok,pocetnaTacka.y+j*ySkok));
            }
        }
    }

    public ArrayList<Point> getConnectionPoints() {
        return connectionPoints;
    }
}
