package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;

import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {

    List<ClassContent> sadrzaj;
    InterclassVidljivost vidljivost;

    public Interclass(String name, ClassyNode parent) {
        super(name, parent);
        vidljivost=InterclassVidljivost.PUBLIC;
        sadrzaj=new ArrayList<>();
    }

    public Interclass(String name, ClassyNode parent, InterclassVidljivost vidljivost) {
        super(name, parent);
        this.vidljivost=vidljivost;
        sadrzaj=new ArrayList<>();
    }

    public void addClassContent(ClassContent classContent)
    {
        sadrzaj.add(classContent);
        //notifySubscribers();
    }

    public List<ClassContent> getClassContent() {
        return sadrzaj;
    }

    //public abstract void changeClassContent(ClassContent classContentkoji, ClassContent novi);

}
