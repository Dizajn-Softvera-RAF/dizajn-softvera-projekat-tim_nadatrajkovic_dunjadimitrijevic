package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    public Project(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO hendlovati exceptione
        if (child != null &&  child instanceof Package){
            Package p = (Package) child;
            if (!this.getChildren().contains(p)){
                this.getChildren().add(p);
            }
        }
    }

    @Override
    public void removeChild() {
        //TODO
    }
}
