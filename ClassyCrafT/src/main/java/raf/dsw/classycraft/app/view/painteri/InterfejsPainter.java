package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;

public class InterfejsPainter extends ElementPainter {
    private Point pocetnaTacka;

    public InterfejsPainter(DiagramElement diagramElement, Point p) {
        super(diagramElement);
        pocetnaTacka = p;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,50,50);
    }

    @Override
    public boolean elementAt(Point P) {
        return false;
    }
}
