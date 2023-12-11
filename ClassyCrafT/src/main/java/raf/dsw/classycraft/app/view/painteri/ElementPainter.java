package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.awt.*;

public abstract class ElementPainter{
    //diagram
    protected DiagramElement diagramElement;
    protected boolean selektovano=false;


    public ElementPainter(DiagramElement diagramElement) {
        this.diagramElement = diagramElement;
    }

    public abstract void draw(Graphics2D g);
    public abstract boolean elementAt(Point P);

    public boolean isSelektovano() {
        return selektovano;
    }

    public void setSelektovano(boolean selektovano) {
        this.selektovano = selektovano;
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

    public DiagramElement getDiagramElement() {
        return diagramElement;
    }
}
