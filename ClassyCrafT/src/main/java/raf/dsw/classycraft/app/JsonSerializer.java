package raf.dsw.classycraft.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.core.Serializer;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonSerializer implements Serializer {
    @Override
    public Project loadProject(File file) {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);

        try {
            Project project=objectMapper.readValue(file,Project.class);
            return project;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveProject(Project node,File projectFile) {

        ObjectMapper objectMapper=new ObjectMapper();
        try {
            System.out.println("doso da sacuva "+node.toString());
            System.out.println("projectFile path: "+ projectFile.getPath()+"------------------------");
            objectMapper.writeValue(projectFile, node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveDiagramAsPattern(Diagram node, File diagramFile) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            System.out.println("doso da sacuva "+node.toString());
            System.out.println("diagramFile path: "+ diagramFile.getPath()+"------------------------");
            objectMapper.writeValue(diagramFile, node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
