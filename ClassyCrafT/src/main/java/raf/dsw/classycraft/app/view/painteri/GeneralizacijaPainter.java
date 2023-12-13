package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class GeneralizacijaPainter extends ConnectionPainter{
    public GeneralizacijaPainter(DiagramElement diagramElement, Point odakle, Point dokle) {
        super(diagramElement, odakle, dokle);
    }

    @Override
    protected Point tackaPresekaDijagonala() {
        int x=dokle.x;
        int y=dokle.y;
        kNormale=-(1/k);

        double xNormala,yNormala;
        int koef=1;
        if(odakle.x<dokle.x)
            koef=-1;
        xNormala=koef*(duzinaDiajgonale/2)/Math.sqrt(k*k+1)+x;
        yNormala=xNormala*k+n;
        if((k<0.1 && k>-0.1)|| Double.isInfinite(k))
            yNormala=koef*y;

        nNormale=yNormala-kNormale*xNormala;

        return new Point((int) xNormala, (int) yNormala);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        System.out.println("tacka od "+odakle+" tacka normale"+ tackaNormale);

        //g.drawRect(tackaNormale.x, tackaNormale.y,5,5);
        //int stranica=7;
        GeneralPath putTrougla=new GeneralPath();

        putTrougla.moveTo(dokle.x,dokle.y);
        putTrougla.lineTo(tackaZaRombiStrelicu1.x,tackaZaRombiStrelicu1.y);
        putTrougla.lineTo(tackaZaRombiStrelicu2.x,tackaZaRombiStrelicu2.y);
        putTrougla.closePath();

        g.setColor(Color.white);
        g.fill(putTrougla);
        g.setColor(Color.black);
        g.draw(putTrougla);

    }

}
