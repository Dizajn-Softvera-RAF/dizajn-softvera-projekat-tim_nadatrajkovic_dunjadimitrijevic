package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;

public interface ClassyRepository {
    ProjectExplorer getRoot();

    ClassyNode createNode(ClassyNode parent, NodeType type);
}
