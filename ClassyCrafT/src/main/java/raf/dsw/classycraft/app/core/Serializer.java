package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    Diagram loadPattern(File file);
    void saveProject(Project node,File projectFile);
    void saveDiagramAsPattern(Diagram node, File diagramFile);

}
