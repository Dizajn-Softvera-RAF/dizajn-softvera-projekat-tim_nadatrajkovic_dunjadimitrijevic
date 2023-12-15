package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Connection;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ZavisnostPainter extends ConnectionPainter{

    public ZavisnostPainter(DiagramElement diagramElement) {
        super(diagramElement);
    }

    @Override
    public void draw(Graphics2D g) {
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g.setStroke(dashed);
        super.draw(g);
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();

        System.out.println("tacka od "+odakle+" tacka normale"+ tackaNormale);

        //g.drawRect(tackaNormale.x, tackaNormale.y,5,5);
        //int stranica=7;


        Stroke obicna=new BasicStroke(2);
        g.setStroke(obicna);

        g.drawLine(odakle.x,odakle.y,tackaZaRombiStrelicu2.x,tackaZaRombiStrelicu2.y);
        g.drawLine(odakle.x,odakle.y,tackaZaRombiStrelicu1.x,tackaZaRombiStrelicu1.y);

    }

    protected Point tackaPresekaDijagonala() //trazimo tacku preseka prave i kruznice poluprecinka dijagonala/2
    {
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        int x=odakle.x;
        int y=odakle.y;
        kNormale=-(1/k);
        System.out.println("k i knormale "+ k+ " "+kNormale);
        //nNormale=odakle.x*(k*k+1)/k+n;
        //if(k>0)//nisam sigurna kako ovo radi al kao treba da ga makne za pola dijagonale ka unutra
        //nNormale+=duzinaDiajgonale/2;
        //else
        //  nNormale-=duzinaDiajgonale/2;

//        double xNormala=k*(nNormale+n)/(k*k+1);
//        double yNormala=xNormala*kNormale+nNormale;
//        return new Point((int) xNormala, (int) yNormala);
//
        double xNormala,yNormala;//(x-xnormala)^2+(y-ynormala)^2=d^2 izrazimo y preko x jer fja
        //na kraju xnormala=+- d/sqrt(k^2+1)+x
        //e sad ne znam kad ide + a kad -
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
}
