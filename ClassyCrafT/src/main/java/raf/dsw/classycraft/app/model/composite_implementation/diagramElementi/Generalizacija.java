package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;

import java.awt.*;

public class Generalizacija extends Connection {
    public Generalizacija(String name, ClassyNode parent, Interclass odInterclass, Interclass doInterclass, Point odTacka, Point doTacka) {
        super(name, parent, odInterclass, doInterclass, odTacka, doTacka);
    }
}
