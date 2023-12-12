package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;

public abstract class ConnectionPainter extends ElementPainter {
    Point odakle;
    Point dokle;
    public ConnectionPainter(DiagramElement diagramElement, Point odakle, Point dokle) {
        super(diagramElement);
        this.odakle=odakle;
        this.dokle=dokle;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawLine(odakle.x,odakle.y,dokle.x,dokle.y);
    }

    @Override
    public boolean elementAt(Point P) {
        return false;
    }
}
