package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;

import java.awt.*;
import java.util.ArrayList;

@JsonTypeName("interfejs")
public class Interfejs extends Interclass {
    public Interfejs(String name, ClassyNode parent, Point pocetnaTacka) {
        super(name, parent, pocetnaTacka);
    }


    @JsonCreator
    public Interfejs(@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("sadrzaj") ArrayList<ClassContent> sadrzaj,
                 @JsonProperty("vidljivost") InterclassVidljivost vidljivost, @JsonProperty("pocetnaTacka") Point pocetnaTacka,
                 @JsonProperty("krajnjaTacka") Point krajnjaTacka, @JsonProperty("width") int width, @JsonProperty("height") int height) {
        super(name, null, pocetnaTacka);
        this.setSadrzaj(sadrzaj);
        this.setVidljivost(vidljivost);
        this.setPocetnaTacka(pocetnaTacka);
        this.setKrajnjaTacka(krajnjaTacka);
        this.setWidth(width);
        this.setHeight(height);
    }
}
