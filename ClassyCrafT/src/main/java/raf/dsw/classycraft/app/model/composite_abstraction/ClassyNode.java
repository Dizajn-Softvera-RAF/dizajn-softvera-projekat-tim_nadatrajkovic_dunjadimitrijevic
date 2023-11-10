package raf.dsw.classycraft.app.model.composite_abstraction;

public abstract class ClassyNode {
    private String name;
    private ClassyNode parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    public ClassyNode getParent() {
        return parent;
    }
}
