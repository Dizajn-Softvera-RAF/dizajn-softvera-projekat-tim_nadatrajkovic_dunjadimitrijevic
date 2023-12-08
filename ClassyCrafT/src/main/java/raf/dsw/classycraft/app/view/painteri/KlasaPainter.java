package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;

public class KlasaPainter extends ElementPainter{
    private Point pocetnaTacka;
    private int width,height;
    public KlasaPainter(DiagramElement diagramElement, Point p, int width, int height) {
        super(diagramElement);
        pocetnaTacka=p;
        this.width=width;
        this.height=height;
    }

    public KlasaPainter(DiagramElement diagramElement, Point pocetnaTacka) {
        super(diagramElement);
        this.pocetnaTacka = pocetnaTacka;
    }

    @Override
    public void draw(Graphics2D g) {

        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,50,50);

    }

    @Override
    public boolean elementAt(Point P) {
        return false;
    }
    //jel ove interclass imaju listu connection pointova za pravljenje veza i onda se gleda najmanje rastojanje dva pointa
}
