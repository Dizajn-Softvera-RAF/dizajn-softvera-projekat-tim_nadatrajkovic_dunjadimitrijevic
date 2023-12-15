package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Connection;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;

public abstract class ConnectionPainter extends ElementPainter {
//    Point odakle;
//    Point dokle;

    double n,k;
    double duzinaDiajgonale=20;
    Point tackaNormale;//(x1,y1), y1=x1*(-1/k)+q //tacka preseka dijagonala romba
                       //         y1=x1*k+n
    //ocu da nadjem tacku na liniji iz koje vucem normalu
    //
    Point tackaStreliceiRomba1,getTackaStreliceiRomba2;
    double nNormale,kNormale;

    Point tackaZaRombiStrelicu1,tackaZaRombiStrelicu2;

    public ConnectionPainter(DiagramElement diagramElement) {
        super(diagramElement);
//        this.odakle=odakle;
//        this.dokle=dokle;
//        napraviFju();
//        tackaNormale = tackaPresekaDijagonala(); //moze da bude kod od il do
//        postaviTackeRombaStrelice();
    }

    private void napraviFju()
    {
        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        k= (double) (odakle.y - dokle.y) /(odakle.x-dokle.x);
        n=odakle.y-k*odakle.x;

    }

    protected void postaviTackeRombaStrelice()
    {
        if(tackaNormale!=null)
        {
            //opet radimo presek prave i kruga samo sto nam je sad prava normala na vezu

            double x1,y1,x2,y2;//(x-xnormala)^2+(y-ynormala)^2=d^2 izrazimo y preko x jer fja
            //na kraju xnormala=+- d/sqrt(k^2+1)+x
            //e sad ne znam kad ide + a kad -


            x1=(duzinaDiajgonale/2)/Math.sqrt(kNormale*kNormale+1)+tackaNormale.x;

            y1=x1*kNormale+nNormale;

            if((k<0.1 && k>-0.1) || Double.isInfinite(k))
                y1=tackaNormale.y+duzinaDiajgonale/2;

            x2=-(duzinaDiajgonale/2)/Math.sqrt(kNormale*kNormale+1)+tackaNormale.x;


            y2=x2*kNormale+nNormale;
            if((k<0.1 && k>-0.1)|| Double.isInfinite(k))
                y2=tackaNormale.y-duzinaDiajgonale/2;

            tackaZaRombiStrelicu1=new Point((int) x1, (int) y1);
            tackaZaRombiStrelicu2=new Point((int) x2, (int) y2);

        }
    }

    protected double odrediX(double y)
    {
        return (y-n)/k;
    }

    protected double odrediY(double x)
    {
        return x*k+n;
    }


    @Override
    public void draw(Graphics2D g){

        if(selektovano)
            g.setColor(Color.green);
        else
            g.setColor(Color.black);
        double minRastojanje = Integer.MAX_VALUE;


        Connection connection = (Connection)this.getDiagramElement();
        Point odakle = null;
        Point dokle = null;
        for (Point po : connection.getInterclassOd().getConnectionPoints()) {
            for (Point pd : connection.getInterclassDo().getConnectionPoints()) {
                if (udaljenost(po, pd) < minRastojanje) {
                    odakle = po;
                    dokle = pd;
                    minRastojanje=udaljenost(po,pd);
                }
            }

        }
        connection.setOdTacka(odakle);
        connection.setDoTacka(dokle);
        napraviFju();
        tackaNormale = tackaPresekaDijagonala(); //moze da bude kod od il do
        postaviTackeRombaStrelice();

//        Point odakle = ((Connection)this.getDiagramElement()).getOdTacka();
//        Point dokle = ((Connection)this.getDiagramElement()).getDoTacka();
        g.drawLine(odakle.x,odakle.y,dokle.x,dokle.y);

        //g.setColor(Color.black);

    }
    private double udaljenost(Point p1, Point p2)
    {
        double a2=(p1.x-p2.x)*(p1.x-p2.x);
        double b2=(p1.y-p2.y)*(p1.y-p2.y);
        System.out.println("udaljenost "+p1+" "+p2+" "+Math.sqrt(a2+b2));
        return Math.sqrt(a2+b2);
    }

    @Override
    public boolean elementAt(Point P) {


        System.out.println("prverava klik "+P);
        if(P.y-P.x*k-n>=-10 && P.y-P.x*k-n<=10)
            return true;
        return false;


    }

    protected abstract Point tackaPresekaDijagonala();

}
