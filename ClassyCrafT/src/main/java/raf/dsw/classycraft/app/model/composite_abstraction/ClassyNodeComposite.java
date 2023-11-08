package raf.dsw.classycraft.app.model.composite_abstraction;


import java.util.ArrayList;

public abstract class ClassyNodeComposite extends ClassyNode{
    private ArrayList<ClassyNode> children;

    public abstract void addChild();
    public abstract void removeChild();
}
