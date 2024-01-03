package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddInterclassCommand extends AbstractCommand {

    private DiagramView dv;
    private Point P;

    private InterclassPainter interclassPainter;
    private Interclass interclass;



    public AddInterclassCommand(DiagramView dv, Interclass interclass, InterclassPainter interclassPainter) {
        this.dv = dv;
        this.interclass = interclass;
        this.interclassPainter=interclassPainter;
    }

    /*private Metoda napraviMetoduOdStringa(String line)
    {
        Pattern pattern = Pattern.compile(
                "\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*\\(((\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+\\s*)(,\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+)*)*\\s*\\)\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);
        if (!line.equals("")) {
            Matcher matcher = pattern.matcher(line);
            System.out.println("linija2 " + line);
            if (matcher.find()) {
                line = line.trim();
                char s0 = line.charAt(0);
                InterclassVidljivost vidljivost;
                if (s0 == '~') {
                    vidljivost = InterclassVidljivost.PROTECTED;
                } else if (s0 == '-') {
                    vidljivost = InterclassVidljivost.PRIVATE;
                } else {
                    vidljivost = InterclassVidljivost.PUBLIC;
                }
                System.out.println("trenutna linija" + line);

                String nazivMetode = line.substring(1, line.indexOf("(")).trim();
                System.out.println("naziv metode trimovanje:"+nazivMetode);
                String povratniTip = line.substring(line.indexOf(":") + 1).trim();
                Metoda metoda = new Metoda(nazivMetode, vidljivost, povratniTip);


                String parametriStr = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
                if (!parametriStr.equals("")) {
                    String[] parametri = parametriStr.split(",");
                    for (String parametar : parametri) {
                        System.out.println(parametar);
                        parametar = parametar.trim();
                        String tip = parametar.substring(0, parametar.indexOf(" "));
                        String nazivParametra = parametar.substring(parametar.indexOf(" ") + 1).trim();
                        metoda.addParametarFunkcije(new Atribut(nazivParametra, InterclassVidljivost.PRIVATE, tip));
                    }
                }

                //interfejs.addClassContent(metoda);
                System.out.println("trenutna metoda" + metoda.toString());
                return metoda;
            }
            else {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Metoda "+ line + " nije uneta kako treba" , MessageType.WARNING);
                //return; // pravice interfejs koji ima ime i metode koje odgovaraju sablonu, a nece dodati lose unete metode
            }
        }
        System.out.println("line je prazna - metoda");
        return null;
    }

    private Atribut napraviAtributOdStringa(String line)
    {
        Pattern pattern = Pattern.compile("\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);

        //+ime: String
        if (!line.equals("")) {
            Matcher matcher = pattern.matcher(line);
            //System.out.println("linija2 " + line);
            if (matcher.find()) {
                line = line.trim();
                char s0 = line.charAt(0);
                InterclassVidljivost vidljivost;
                if (s0 == '~') {
                    vidljivost = InterclassVidljivost.PROTECTED;
                } else if (s0 == '-') {
                    vidljivost = InterclassVidljivost.PRIVATE;
                } else {
                    vidljivost = InterclassVidljivost.PUBLIC;
                }
                //System.out.println("trenutna linija" + line);

                String nazivAtributa = line.substring(1, line.indexOf(":")).trim();
                //System.out.println("naziv atributa trimovanje:"+nazivAtributa);
                String tip = line.substring(line.indexOf(":") + 1).trim();
                Atribut atribut = new Atribut(nazivAtributa, vidljivost, tip);

                System.out.println("trenutna metoda" + atribut.toString());
                return atribut;
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Atribut " + line + " nije unet kako treba", MessageType.WARNING);
                //return; // pravice interfejs koji ima ime i metode koje odgovaraju sablonu, a nece dodati lose unete metode
            }
        }
        System.out.println("line je prazna - atribut");
        return null;
    }
    private ClanEnumeracije napraviClanEnumaOdStringa(String line)
    {
        Pattern pattern = Pattern.compile("\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);
        if(!line.equals(""))
        {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                line = line.trim();
                ClanEnumeracije ce = new ClanEnumeracije(line);
                return ce;
            }
            else
            {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Clan enumeracije " + line + " nije unet kako treba", MessageType.WARNING);
            }
        }
        System.out.println("line je prazna - clan enumeracije");
        return null;
    }*/

    @Override
    public void doCommand() {
        //@Override
        //public void misPritisnut(Point P, DiagramView dv) {
/*
        Object[] options = {"Klasa","Interfejs","Enum"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        //MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa", JOptionPane., JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        if(choice == 0)//crta klasu
        {

            //System.out.println("item"+item.toString());
            System.out.println( dv.getDiagram().getChildren().toString()+" ovo su parentova deca");

            JTextField nazivtf=new JTextField();
            JTextArea poljata=new JTextArea();
            JTextArea metodeta=new JTextArea();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeKlase"),
                    nazivtf,
                    new JLabel("Polja razdvojena tacka zarezom, primer: +ime: String"),
                    poljata,
                    new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };

//            JOptionPane novaKlasa=new JOptionPane(inputs);
//            novaKlasa.setSize(500,500);
//            novaKlasa.createDialog(MainFrame.getInstance(),"nova klasa");
//            novaKlasa.setVisible(true);

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(rez == 0) // ako je kliknut OK
            {
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se klasa kreirala", MessageType.WARNING);
                    // jos nesto?
                }
                else
                {
                    Klasa klasa = new Klasa(s, dv.getDiagram(), P);
                    //+ime: String
                    //Pattern polja_patern = Pattern.compile("\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);

                    if(!poljata.getText().trim().equals(""))
                    {
                        for(String line : poljata.getText().split(";"))
                        {
                            Atribut atribut = napraviAtributOdStringa(line);
                            if(atribut != null)
                                klasa.addClassContent(atribut);
                        }
                    }

                    if(!metodeta.getText().trim().equals(""))
                    {
                        for (String line : metodeta.getText().split(";")) {
                            //System.out.println("linija1 " + line);
                            Metoda metoda = napraviMetoduOdStringa(line);
                            if(metoda != null)
                                klasa.addClassContent(metoda);
                        }
                    }
                    interclassPainter=new KlasaPainter(klasa);
                    dv.addPainter(interclassPainter);
                    klasa.addSubscriber(dv); // jel dv ili klasaPainter? mislila bih da je klasaPainter ali mi treba Graphics g za draw()..

                    MainFrame.getInstance().getClassyTree().addDiagramElement(item, klasa);
                }
            }
            else if (rez == 1)
            {
                System.out.println("Kliknut Cancel kod dodavanja klase");
            }

        }
        if(choice == 1)//crta interfejs
        {
            JTextField nazivtf=new JTextField();

            JTextArea metodeta=new JTextArea();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeInterfejsa"),
                    nazivtf,
                    new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novInterfejs",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(rez == 0) // ako je kliknut OK
            {
                System.out.println("kliknut OK");
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se interfejs kreirao", MessageType.WARNING);
                    // jos nesto?
                }
                else
                {
                    Interfejs interfejs = new Interfejs(s,dv.getDiagram(), P);
                    //System.out.println("metode " + metodeta.getText());

//            Pattern patern = Pattern.compile(
//                    "[+~-][a-zA-z0-9_]+\\((([a-zA-z0-9_]+\\s[a-zA-z0-9_]+)(,\\s*[a-zA-z0-9_]+\\s[a-zA-z0-9_]+)*)*\\):[a-zA-z0-9_]+", Pattern.CASE_INSENSITIVE);

                    // \\s* da moze koirsnik da unese i nepotrebne razmake

//                    Matcher matcher = patern.matcher("-metoda1(int a,String s):int");
//                    System.out.println(matcher.find());
                    if(!metodeta.getText().trim().equals(""))
                    {
                        for (String line : metodeta.getText().split(";")) {
                            System.out.println("linija1 " + line);
                            Metoda metoda = napraviMetoduOdStringa(line);
                            if(metoda != null)
                                interfejs.addClassContent(metoda);
                        }
                    }

                    interclassPainter=new InterfejsPainter(interfejs);
                    dv.addPainter(interclassPainter);
                    interfejs.addSubscriber(dv);

                    MainFrame.getInstance().getClassyTree().addDiagramElement(item, interfejs);

                }
            }
            else if(rez == 1)
            {
                System.out.println("Kliknut Cancel kod dodavanja interfejsa");
                // ako je kliknut cancel - onda nista
            }
        }

        if(choice==2)//crta enumeraciju
        {
            JTextField nazivtf=new JTextField();

            JTextField enumeracije=new JTextField();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("Ime enumeracije"),
                    nazivtf,
                    new JLabel("Upisi elemente enumeracije razdvojene zarezom"),
                    enumeracije
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"nova enumeracija",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(rez == 0)
            {
                System.out.println("kliknut OK");
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se enumeracija kreirala", MessageType.WARNING);
                }
                else
                {
                    Enumeracija enumeracija = new Enumeracija(s,dv.getDiagram(), P);
                    if(!enumeracije.getText().trim().equals(""))
                    {
                        for (String line : enumeracije.getText().split(",")) {
                            //System.out.println("linija1 " + line);
                            ClanEnumeracije clanEnumeracije = napraviClanEnumaOdStringa(line);
                            if(clanEnumeracije != null)
                                enumeracija.addClassContent(clanEnumeracije);
                        }
                    }

                    interclassPainter = new EnumeracijaPainter(enumeracija);
                    dv.addPainter(interclassPainter);
                    enumeracija.addSubscriber(dv);

                    MainFrame.getInstance().getClassyTree().addDiagramElement(item, enumeracija);
                }
            }
            else if(rez == 1)
            {
                System.out.println("Kliknut Cancel kod dodavanja enumeracije");
            }
        }*/

/*
        if(interclass instanceof Klasa)
        {
            interclassPainter = new KlasaPainter(interclass);
        }
        if(interclass instanceof Interfejs)
        {
            interclassPainter = new InterfejsPainter(interclass);
        }
        if(interclass instanceof Enumeracija)
        {
            interclassPainter = new EnumeracijaPainter(interclass);
        }

 */
        dv.addPainter(interclassPainter);
        interclass.addSubscriber(dv);


        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        MainFrame.getInstance().getClassyTree().addDiagramElement(item, interclass);

    }

    @Override
    public void undoCommand() {
        dv.getPainterList().remove(interclassPainter);
        dv.repaint();

        ClassyTreeView treeView=MainFrame.getInstance().getClassyTree().getTreeView();
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(interclassPainter.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
        item.removeFromParent();

        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
