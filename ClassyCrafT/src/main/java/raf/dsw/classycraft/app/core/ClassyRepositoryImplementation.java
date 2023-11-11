package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository{
    private ProjectExplorer root;

    public ClassyRepositoryImplementation() {
        this.root = new ProjectExplorer("Project Explorer");
        System.out.println("napravio project explorer " + root.getName() + " " + this);
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }
}
