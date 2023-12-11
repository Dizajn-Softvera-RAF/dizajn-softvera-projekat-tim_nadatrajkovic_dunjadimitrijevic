package raf.dsw.classycraft.app.view.painteri;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;

import java.awt.*;
import java.util.ArrayList;

public class KlasaPainter extends InterclassPainter{
    private Point pocetnaTacka;

    private ArrayList<Point> connectionPoints;
    private int width,height;
    public KlasaPainter(DiagramElement diagramElement, Point p, int width, int height) {
        super(diagramElement);
        pocetnaTacka=p;
        this.width=width;
        this.height=height;
        connectionPoints=new ArrayList<>();
    }

    public KlasaPainter(DiagramElement diagramElement, Point p) {
        super(diagramElement,p);

    }
    //jel ove interclass imaju listu connection pointova za pravljenje veza i onda se gleda najmanje rastojanje dva pointa
}
