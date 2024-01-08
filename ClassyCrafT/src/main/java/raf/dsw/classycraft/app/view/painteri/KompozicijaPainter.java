package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Connection;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class KompozicijaPainter extends ConnectionPainter{
    Point cetvrtaTacka;
    public KompozicijaPainter(DiagramElement diagramElement) {
        super(diagramElement);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        System.out.println("tacka od "+odakle+" tacka normale"+ tackaNormale);

        //g.drawRect(tackaNormale.x, tackaNormale.y,5,5);
        //int stranica=7;
        GeneralPath putRomba=new GeneralPath();

        putRomba.moveTo(odakle.x,odakle.y);
        putRomba.lineTo(tackaZaRombiStrelicu1.x,tackaZaRombiStrelicu1.y);
        cetvrtaTacka=cetvrtaTacka();
        putRomba.lineTo(cetvrtaTacka.x,cetvrtaTacka.y);
        putRomba.lineTo(tackaZaRombiStrelicu2.x,tackaZaRombiStrelicu2.y);
        putRomba.closePath();

        g.setColor(Color.black);
        g.fill(putRomba);
    }

    @Override
    protected Point tackaPresekaDijagonala() {
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        int x=odakle.x;
        int y=odakle.y;
        kNormale=-(1/k);

        double xNormala,yNormala;
        int koef=1;
        if(odakle.x>dokle.x)
            koef=-1;
        xNormala=koef*(duzinaDiajgonale/2)/Math.sqrt(k*k+1)+x;
        yNormala=xNormala*k+n;
        if((k<0.1 && k>-0.1)|| Double.isInfinite(k))
            yNormala=koef*y;

        nNormale=yNormala-kNormale*xNormala;

        return new Point((int) xNormala, (int) yNormala);
    }

    protected Point cetvrtaTacka()//za romb
    {
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        int x=odakle.x;
        int y=odakle.y;
        double xNormala,yNormala;//(x-xnormala)^2+(y-ynormala)^2=d^2 izrazimo y preko x jer fja
        //na kraju xnormala=+- d/sqrt(k^2+1)+x
        //e sad ne znam kad ide + a kad -
        int koef=1;
        if(odakle.x>dokle.x)
            koef=-1;
        xNormala=koef*(duzinaDiajgonale)/Math.sqrt(k*k+1)+x;
        yNormala=xNormala*k+n;
        return new Point((int) xNormala, (int) yNormala);
    }
}
