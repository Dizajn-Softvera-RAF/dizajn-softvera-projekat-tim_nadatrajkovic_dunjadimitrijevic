package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Enumeracija;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;

import java.awt.*;

public class InterfejsPainter extends ElementPainter {
    private Point pocetnaTacka;
    private int width,height;

    public InterfejsPainter(DiagramElement diagramElement, Point p) {
        super(diagramElement);
        pocetnaTacka = p;
    }

    @Override
    public void draw(Graphics2D g) {

        //ima interfejs sa imenom i metodama samo treba da ih ispise

        String ime=diagramElement.getName();
        int velicinaFonta=g.getFont().getSize();

        System.out.println("velicina fonta interfejs "+velicinaFonta);



        int maxSize=duzinaReci(ime,g);
        System.out.println("maxSize poc"+maxSize);

        Point koordinateMetode=new Point(pocetnaTacka.x+velicinaFonta, pocetnaTacka.y+3*velicinaFonta);;

        if(diagramElement instanceof Interfejs)
        {
            Interfejs interfejs=(Interfejs) diagramElement;

            for (ClassContent c: interfejs.getClassContent()) {

                g.drawString(c.toString(),koordinateMetode.x, koordinateMetode.y);
                koordinateMetode.y+=velicinaFonta;
                maxSize=Math.max(maxSize,duzinaReci(c.toString(),g));
            }
        }


        width=maxSize+2*velicinaFonta;

        System.out.println("maxsize i width "+maxSize+" "+width);

        height= koordinateMetode.y - pocetnaTacka.y;

        g.drawString(ime,pocetnaTacka.x+(width-duzinaReci(ime,g))/2, pocetnaTacka.y+velicinaFonta);

        g.drawRect(pocetnaTacka.x,pocetnaTacka.y,width ,height);
    }

    @Override
    public boolean elementAt(Point P) {
        return false;
    }
}
