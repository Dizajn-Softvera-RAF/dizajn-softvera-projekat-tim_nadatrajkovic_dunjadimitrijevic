package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {

    List<ClassContent> sadrzaj;
    InterclassVidljivost vidljivost;
    private Point pocetnaTacka;
    private Point krajnjaTacka;
    private int width;
    private int height;
//    private int maxSlova; // ovo nam kao treba za width posle u painteru
//    private int brSadrzaja; // ovo nam kao treba za heigth posle u peinteru

//    private void izracunajDimenzije(){
//        int maxSize=this.getName().length();
//        for (ClassContent c: this.getClassContent()) {
//            maxSize = Math.max(maxSize, c.toString().length());
//        }
//        maxSlova = maxSize;
//    }

//    private int brojSlova(String rec)
//    {
//        int duzina=0;
//        for(int i=0;i<rec.length();i++) {
//            int jedan=g.getFontMetrics().charWidth(rec.charAt(i));
//            duzina+=jedan;
//        }
//        return duzina;
//    }

    public Interclass(String name, ClassyNode parent, Point pocetnaTacka) {
        super(name, parent);
        vidljivost=InterclassVidljivost.PUBLIC;
        sadrzaj=new ArrayList<>();
        this.pocetnaTacka = pocetnaTacka;
    }

//    public Interclass(String name, ClassyNode parent, InterclassVidljivost vidljivost, Point pocetnaTacka) {
//        super(name, parent);
//        this.vidljivost=vidljivost;
//        sadrzaj=new ArrayList<>();
//        this.pocetnaTacka = pocetnaTacka;
//    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    public void addClassContent(ClassContent classContent)
    {
        sadrzaj.add(classContent);
        //notifySubscribers();
    }

    public Point getPocetnaTacka() {
        return pocetnaTacka;
    }

    public void setPocetnaTacka(Point pocetnaTacka) {
        this.pocetnaTacka = pocetnaTacka;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getKrajnjaTacka() { // todo videti kako width, heigth i krajnju tacku resiti u peinteru
        return krajnjaTacka;
    }

    public void setKrajnjaTacka(int w, int h) {
        width = w;
        height = h;
        this.krajnjaTacka = new Point(pocetnaTacka.x + width, pocetnaTacka.y + height);
    }

    public InterclassVidljivost getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(InterclassVidljivost vidljivost) {
        this.vidljivost = vidljivost;
    }

    public List<ClassContent> getClassContent() {
        return sadrzaj;
    }

    //public abstract void changeClassContent(ClassContent classContentkoji, ClassContent novi);

}
