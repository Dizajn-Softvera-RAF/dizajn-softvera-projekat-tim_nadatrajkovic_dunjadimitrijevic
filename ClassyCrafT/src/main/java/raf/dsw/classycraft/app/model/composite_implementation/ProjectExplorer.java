package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {


    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO hendlovati exceptionse
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
//        if(child == null || !(child instanceof Project))
//            return;
//        this.getChildren().add((Project) child);
    }

    @Override
    public void removeChild() {
        //TODO
    }
}
