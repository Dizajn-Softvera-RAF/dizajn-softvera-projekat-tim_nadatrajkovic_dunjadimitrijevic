package raf.dsw.classycraft.app.model.composite_implementation;

import com.fasterxml.jackson.annotation.*;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

import java.util.ArrayList;

@JsonTypeName("project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends ClassyNodeComposite {
    protected String filePath;
    @JsonIgnore
    transient protected boolean changed = true;

    private String imeAutora;

    private static int brojacProjekata=1;


    public Project(String name, ClassyNode parent) {
        super(name, parent);
        imeAutora="";
        brojacProjekata++;
    }
    @JsonCreator
    public Project(@JsonProperty("type") String tip, @JsonProperty("name") String name, @JsonProperty("children") ArrayList<ClassyNode> children, @JsonProperty("imeAutora") String imeAutora, @JsonProperty("filePath") String filePath) {
        super(name, null);
        this.setChildren(children);
        this.imeAutora=imeAutora;
        this.filePath = filePath;
        brojacProjekata++;
    }

    public Project(ClassyNode parent)
    {
        super("projekat"+brojacProjekata,parent);
        imeAutora="";
        brojacProjekata++;
    }

    @Override
    public void addChild(ClassyNode child) {
        //TODO hendlovati exceptione
        if (child != null &&  child instanceof Package){
            Package p = (Package) child;
            if (!this.getChildren().contains(p)){
                this.getChildren().add(p);
            }
        }
        this.setChanged(true);
    }

    @Override
    public void removeChild(ClassyNode child) {
        //TODO
        this.setChanged(true);
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
        notifySubscribers(new Notification(this, NotificationType.RENAME));
        System.out.println("promenio ime autora "+this.imeAutora);
        setChanged(true);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
