package raf.dsw.classycraft.app.model.composite_implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;

import java.util.ArrayList;

//a neki static brojac za default ime

@JsonTypeName("diagram")
public class Diagram extends ClassyNodeComposite implements IPublisher {

    public static int brojacDijagrama=1;
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    @JsonCreator
    public Diagram(@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("children") ArrayList<ClassyNode> children) {
        super(name, null);
        this.setChildren(children);
        brojacDijagrama++;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof DiagramElement){
            DiagramElement de = (DiagramElement) child;
            if (!this.getChildren().contains(de)){
                this.getChildren().add(de);//jel ovde notify subscribers da se osvezi diagram view da repaintuje?
            }
        }
    }

    public Diagram(ClassyNode parent) {
        super("dijagram"+brojacDijagrama,parent);
        brojacDijagrama++;
    }
    public void copyPattern(Diagram pattern)
    {
//        for(var subscriber : pattern.getSubscriberList())
//        {
//            // ne znamm
//        }
        //for(var )
    }
}
