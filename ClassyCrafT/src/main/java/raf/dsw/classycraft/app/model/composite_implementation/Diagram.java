package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

//a neki static brojac za default ime

public class Diagram extends ClassyNodeComposite implements IPublisher {

    public static int brojacDijagrama=1;
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof DiagramElement){
            DiagramElement de = (DiagramElement) child;
            if (!this.getChildren().contains(de)){
                this.getChildren().add(de);
            }
        }
    }

    public Diagram(ClassyNode parent) {
        super("dijagram"+brojacDijagrama,parent);
        brojacDijagrama++;
    }

}
