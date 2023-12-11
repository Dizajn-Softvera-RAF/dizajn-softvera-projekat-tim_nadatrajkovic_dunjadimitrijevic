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

    protected ArrayList<Point> connectionPoints;
    protected int width,height;

    public InterclassPainter(DiagramElement diagramElement) {
        super(diagramElement);
    }

    public InterclassPainter(DiagramElement diagramElement, Point p) {
        super(diagramElement);
        pocetnaTacka = p;
        connectionPoints=new ArrayList<>();
    }

    @Override
    public void draw(Graphics2D g) {

        String ime=diagramElement.getName();

        int velicinaFonta=g.getFont().getSize();

        int maxSize=duzinaReci(ime,g);
        System.out.println("maxSize poc"+maxSize);

        Point koordinatePoljaInterklase=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        if(diagramElement instanceof Interclass)
        {
            Interclass interclass=(Interclass) diagramElement;

            for (ClassContent c: interclass.getClassContent()) {

                if (c instanceof Atribut)
                {
                    g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                    koordinatePoljaInterklase.y += velicinaFonta;
                    maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));

                }
            }

            for (ClassContent c: interclass.getClassContent()) {

                if(c instanceof Metoda)
                {
                    g.drawString(c.toString(), koordinatePoljaInterklase.x, koordinatePoljaInterklase.y);
                    koordinatePoljaInterklase.y += velicinaFonta;
                    maxSize = Math.max(maxSize, duzinaReci(c.toString(), g));
                }
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

        System.out.println("maxsize i width "+maxSize+" "+width);

        height= koordinatePoljaInterklase.y - pocetnaTacka.y;

        g.drawString(ime,pocetnaTacka.x+(width-duzinaReci(ime,g))/2, pocetnaTacka.y+velicinaFonta);

        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,width ,height);

        dodajConnectonPoints();
    }

    @Override
    public boolean elementAt(Point p) {
        if((p.x> pocetnaTacka.x && p.x< pocetnaTacka.x+width) && (p.y> pocetnaTacka.y && p.y< pocetnaTacka.x+height))
            return true;
        return false;
    }

    private void dodajConnectonPoints()
    {
        int xSkok=width/2;
        int ySkok=height/2;

        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                if(!(j==1 && i==1))
                    connectionPoints.add(new Point(pocetnaTacka.x+i*xSkok,pocetnaTacka.y+ySkok));
            }
        }
    }
}
