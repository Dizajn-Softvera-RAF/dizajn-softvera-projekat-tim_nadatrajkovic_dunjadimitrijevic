package raf.dsw.classycraft.app.model.composite_implementation;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public static int brojacPaketa=1;
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Package(ClassyNode parent)
    {
        super("package"+ brojacPaketa,parent);
        brojacPaketa++;
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO exceptioni?
        if (child != null &&  child instanceof Package){
            Package p = (Package) child;
            if (!this.getChildren().contains(p)){
                this.getChildren().add(p);
            }
        }
        else if (child != null &&  child instanceof Diagram){
            Diagram d = (Diagram) child;
            if (!this.getChildren().contains(d)){
                this.getChildren().add(d);
            }
        }
    }

    @Override
    public void removeChild() {
        //TODO
    }
}
