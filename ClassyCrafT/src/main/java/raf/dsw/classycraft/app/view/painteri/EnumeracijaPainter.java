package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Enumeracija;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;

import java.awt.*;

public class EnumeracijaPainter extends ElementPainter{

    Point pocetnaTacka;
    private int width,height;

    public EnumeracijaPainter(DiagramElement diagramElement, Point pocetnaTacka) {
        super(diagramElement);
        this.pocetnaTacka = pocetnaTacka;
        width=100;
        height=100;
    }

    @Override
    public void draw(Graphics2D g) {
        String ime=diagramElement.getName();
        int velicinaFonta=g.getFont().getSize();

        int maxSize=ime.length();

        Point koordinateElEnuma=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        if(diagramElement instanceof Enumeracija)
        {
            Enumeracija enumeracija=(Enumeracija) diagramElement;

            for (ClassContent c: enumeracija.getClassContent()) {
                g.drawString(c.toString()+",",koordinateElEnuma.x, koordinateElEnuma.y);
                koordinateElEnuma.y+=velicinaFonta;
                maxSize=Math.max(maxSize,c.toString().length());
            }
        }

        width=pocetnaTacka.x+maxSize*velicinaFonta+2*velicinaFonta;
        height=koordinateElEnuma.y- pocetnaTacka.y;

        g.drawString(ime,pocetnaTacka.x+(width-ime.length()*velicinaFonta)/2, pocetnaTacka.y+velicinaFonta);


        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,width ,height);
    }

    @Override
    public boolean elementAt(Point P) {
        return false;
    }
}
