package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository{
    private ProjectExplorer root;

    public ClassyRepositoryImplementation() {
        this.root = new ProjectExplorer();
    }

    @Override
    public ClassyNode getRoot() {
        return root;
    }
}
